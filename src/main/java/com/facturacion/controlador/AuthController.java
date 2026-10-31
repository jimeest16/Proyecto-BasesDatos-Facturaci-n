package com.facturacion.controlador;

import java.util.Map;

import com.facturacion.dto.LoginRequest;
import com.facturacion.dto.LoginResponse;
import com.facturacion.servicio.AuthService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> iniciarSesion(
            @RequestBody LoginRequest solicitud,
            HttpServletRequest request) {

        String rol = authService.iniciarSesion(
            solicitud.getUsuario(),
            solicitud.getContrasena()
        );

        if (rol == null) {
            LoginResponse respuesta = new LoginResponse(
                false, null, "Usuario, contraseña o rol no válido."
            );
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(respuesta);
        }

        // Si ya había una sesión abierta, se cierra (y se registra su salida).
        HttpSession anterior = request.getSession(false);
        if (anterior != null) {
            cerrarEnBitacora(anterior);
            anterior.invalidate();
        }

        Integer codSesion = authService.registrarInicioSesion(
            solicitud.getUsuario(),
            solicitud.getContrasena(),
            request.getRemoteHost(),   // nombre de la terminal
            request.getRemoteAddr()    // dirección IP
        );

        HttpSession sesion = request.getSession(true);
        sesion.setAttribute("usuario", solicitud.getUsuario());
        sesion.setAttribute("clave", solicitud.getContrasena());
        sesion.setAttribute("rol", rol);
        sesion.setAttribute("codSesion", codSesion);
        sesion.setMaxInactiveInterval(30 * 60);   // 30 minutos de inactividad

        return ResponseEntity.ok(new LoginResponse(true, rol, "Inicio de sesión exitoso."));
    }

    // Las pantallas lo llaman al cargar para saber quién está conectado y con qué rol.
    @GetMapping("/sesion")
    public ResponseEntity<Map<String, String>> sesionActual(HttpServletRequest request) {
        HttpSession sesion = request.getSession(false);
        if (sesion == null || sesion.getAttribute("usuario") == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(Map.of(
            "usuario", (String) sesion.getAttribute("usuario"),
            "rol", (String) sesion.getAttribute("rol")
        ));
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> cerrarSesion(HttpServletRequest request) {
        HttpSession sesion = request.getSession(false);
        if (sesion != null) {
            cerrarEnBitacora(sesion);
            sesion.invalidate();
        }
        return ResponseEntity.noContent().build();
    }

    private void cerrarEnBitacora(HttpSession sesion) {
        authService.registrarSalidaSesion(
            (String) sesion.getAttribute("usuario"),
            (String) sesion.getAttribute("clave"),
            (Integer) sesion.getAttribute("codSesion")
        );
    }
}