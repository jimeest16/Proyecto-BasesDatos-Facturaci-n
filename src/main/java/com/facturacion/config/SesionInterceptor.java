package com.facturacion.config;

import java.io.IOException;

import org.springframework.jdbc.datasource.UserCredentialsDataSourceAdapter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * REQ 003 y REQ 004: ninguna operación se ejecuta sin sesión y cada rol solo
 * llega a sus módulos.
 *   Rol_Admin      acceso completo.
 *   Rol_Digitador  clientes (consultar, registrar, modificar), facturas y notas
 *                  (consultar y registrar); productos e impuestos solo lectura.
 *   Rol_Consulta   solo reportería.
 * Además asigna a la petición las credenciales SQL del usuario.
 */
@Component
public class SesionInterceptor implements HandlerInterceptor {

    private final UserCredentialsDataSourceAdapter dataSource;

    public SesionInterceptor(UserCredentialsDataSourceAdapter dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws IOException {

        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            return true;
        }

        HttpSession sesion = request.getSession(false);
        if (sesion == null || sesion.getAttribute("usuario") == null) {
            return rechazar(response, 401, "Debe iniciar sesión.");
        }

        String usuario = (String) sesion.getAttribute("usuario");
        String clave = (String) sesion.getAttribute("clave");
        String rol = (String) sesion.getAttribute("rol");

        String ruta = request.getRequestURI().substring(request.getContextPath().length());
        String metodo = request.getMethod().toUpperCase();

        if (!permitido(rol, ruta, metodo)) {
            return rechazar(response, 403, "Su rol no tiene permiso para esta operación.");
        }

        dataSource.setCredentialsForCurrentThread(usuario, clave);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) {
        dataSource.removeCredentialsFromCurrentThread();
    }

    static boolean permitido(String rol, String ruta, String metodo) {
        if ("Rol_Admin".equals(rol)) {
            return true;
        }
        if (ruta.startsWith("/api/reportes")) {
            // Consulta solo ve los reportes de clientes y de facturas;
            // auditoría y configuración de seguridad quedan para el administrador.
            if ("Rol_Consulta".equals(rol)) {
                return ruta.startsWith("/api/reportes/clientes") || ruta.startsWith("/api/reportes/facturas");
            }
            return false;
        }
        if ("Rol_Digitador".equals(rol)) {
            if (ruta.startsWith("/api/clientes")) {
                return !metodo.equals("DELETE");
            }
            if (ruta.startsWith("/api/facturas") || ruta.startsWith("/api/notas")) {
                return metodo.equals("GET") || metodo.equals("POST");
            }
            if (ruta.startsWith("/api/productos") || ruta.startsWith("/api/tipos-impuesto")) {
                return metodo.equals("GET");
            }
        }
        return false;
    }

    private boolean rechazar(HttpServletResponse response, int estado, String mensaje) throws IOException {
        response.setStatus(estado);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write("{\"mensaje\":\"" + mensaje + "\"}");
        return false;
    }
}