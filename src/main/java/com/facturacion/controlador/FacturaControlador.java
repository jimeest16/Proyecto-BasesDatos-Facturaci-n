package com.facturacion.controlador;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.facturacion.dto.FacturaDTO;
import com.facturacion.servicio.FacturaServicio;

@RestController
@RequestMapping("/facturas")
public class FacturaControlador {

    private final FacturaServicio servicio;

    public FacturaControlador(FacturaServicio servicio) {
        this.servicio = servicio;
    }

    @GetMapping
    public ResponseEntity<List<FacturaDTO>> getAll() {
        return ResponseEntity.ok(servicio.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FacturaDTO> getById(@PathVariable int id) {
        FacturaDTO dto = servicio.findById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<FacturaDTO> create(@RequestBody FacturaDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(servicio.crearFactura(dto));
    }
}