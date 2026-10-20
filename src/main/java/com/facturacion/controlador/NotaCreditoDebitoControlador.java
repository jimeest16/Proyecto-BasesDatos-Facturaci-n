package com.facturacion.controlador;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.facturacion.dto.NotaCreditoDebitoDTO;
import com.facturacion.servicio.NotaCreditoDebitoServicio;

@RestController
@RequestMapping("/notas")
public class NotaCreditoDebitoControlador {

    private final NotaCreditoDebitoServicio servicio;

    public NotaCreditoDebitoControlador(NotaCreditoDebitoServicio servicio) {
        this.servicio = servicio;
    }

    @GetMapping
    public ResponseEntity<List<NotaCreditoDebitoDTO>> getAll() {
        return ResponseEntity.ok(servicio.findAll());
    }

    @PostMapping
    public ResponseEntity<NotaCreditoDebitoDTO> create(@RequestBody NotaCreditoDebitoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(servicio.save(dto));
    }
}