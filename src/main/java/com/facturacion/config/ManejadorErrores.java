package com.facturacion.config;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Devuelve un mensaje corto y legible en lugar de la traza completa.
 * Si SQL Server rechaza por permisos (REQ 008), responde 403.
 */
@RestControllerAdvice
public class ManejadorErrores {

    @ExceptionHandler({DataAccessException.class, SQLException.class})
    public ResponseEntity<Map<String, String>> errorBaseDatos(Exception e) {
        String mensaje = causaRaiz(e);
        String minusculas = mensaje.toLowerCase();
        HttpStatus estado = (minusculas.contains("permission") || minusculas.contains("permiso"))
                ? HttpStatus.FORBIDDEN
                : HttpStatus.INTERNAL_SERVER_ERROR;
        return ResponseEntity.status(estado).body(Map.of("mensaje", mensaje));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> datosInvalidos(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(Map.of("mensaje", e.getMessage() != null ? e.getMessage() : "Datos inválidos."));
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<Map<String, String>> conflicto(IllegalStateException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("mensaje", e.getMessage() != null ? e.getMessage() : "Operación no permitida."));
    }

    private static String causaRaiz(Throwable e) {
        Throwable t = e;
        while (t.getCause() != null && t.getCause() != t) {
            t = t.getCause();
        }
        return t.getMessage() != null ? t.getMessage() : e.toString();
    }
}