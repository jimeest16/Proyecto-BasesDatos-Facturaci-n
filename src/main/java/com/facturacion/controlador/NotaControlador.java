package com.facturacion.controlador;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.facturacion.dto.NotaCreditoDebitoDTO;
import com.facturacion.servicio.NotaServicio;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/notas")
public class NotaControlador {

    private final NotaServicio notaServicio;

    public NotaControlador(NotaServicio notaServicio) {
        this.notaServicio = notaServicio;
    }

    @GetMapping
    public ResponseEntity<List<NotaCreditoDebitoDTO>> getAllNotas() {
        return ResponseEntity.ok(notaServicio.findAll());
    }

    @PostMapping
    public ResponseEntity<NotaCreditoDebitoDTO> createNota(@RequestBody NotaCreditoDebitoDTO request) {
        NotaCreditoDebitoDTO nuevaNota = notaServicio.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaNota);
    }
}