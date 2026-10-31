package com.facturacion.controlador;

import java.sql.SQLException;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.facturacion.servicio.ReporteServicio;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reportes")
public class ReporteControlador {

    private final ReporteServicio servicio;

    public ReporteControlador(ReporteServicio servicio) {
        this.servicio = servicio;
    }

    @GetMapping("/{nombre}")
    public ResponseEntity<?> ejecutar(
            @PathVariable String nombre,
            @RequestParam(required = false) String desde,
            @RequestParam(required = false) String hasta,
            @RequestParam(required = false) String estado,
            @RequestParam(required = false) String usuario,
            HttpServletRequest request) {

        HttpSession sesion = request.getSession(false);
        if (sesion == null || sesion.getAttribute("usuario") == null) {
            return respuesta(HttpStatus.UNAUTHORIZED, "Debe iniciar sesión.");
        }
        String rol = (String) sesion.getAttribute("rol");
        if (!"Rol_Admin".equals(rol) && !"Rol_Consulta".equals(rol)) {
            return respuesta(HttpStatus.FORBIDDEN, "Su rol no tiene acceso a la reportería.");
        }

        Map<String, String> parametros = new HashMap<>();
        parametros.put("desde", desde);
        parametros.put("hasta", hasta);
        parametros.put("estado", estado);
        parametros.put("usuario", usuario);

        try {
            List<Map<String, Object>> filas = servicio.ejecutar(
                nombre,
                (String) sesion.getAttribute("usuario"),
                (String) sesion.getAttribute("clave"),
                parametros);
            return ResponseEntity.ok(filas);
        } catch (IllegalArgumentException | DateTimeParseException e) {
            return respuesta(HttpStatus.BAD_REQUEST, "Parámetros inválidos: " + e.getMessage());
        } catch (SQLException e) {
            return respuesta(HttpStatus.INTERNAL_SERVER_ERROR,
                "Error al ejecutar el reporte: " + e.getMessage());
        }
    }

    private ResponseEntity<Map<String, String>> respuesta(HttpStatus estado, String mensaje) {
        return ResponseEntity.status(estado).body(Map.of("mensaje", mensaje));
    }
}