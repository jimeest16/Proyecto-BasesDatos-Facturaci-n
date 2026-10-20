
package com.facturacion.controlador;

import com.facturacion.dto.LoginRequest;
import com.facturacion.dto.LoginResponse;
import com.facturacion.servicio.AuthService;

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
            @RequestBody LoginRequest solicitud) {

        String rol = authService.iniciarSesion(
            solicitud.getUsuario(),
            solicitud.getContrasena()
        );

        if (rol == null) {
            LoginResponse respuesta = new LoginResponse(
                false,
                null,
                "Usuario, contraseña o rol no válido."
            );

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(respuesta);
        }

        LoginResponse respuesta = new LoginResponse(
            true,
            rol,
            "Inicio de sesión exitoso."
        );

        return ResponseEntity.ok(respuesta);
    }
}
