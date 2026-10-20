package com.facturacion.controlador;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.facturacion.dto.FacturaDTO;
import com.facturacion.servicio.FacturaServicio;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/facturas")
public class FacturaControlador {

    private final FacturaServicio facturaServicio;

    public FacturaControlador(FacturaServicio facturaServicio) {
        this.facturaServicio = facturaServicio;
    }

    @GetMapping
    public ResponseEntity<List<FacturaDTO>> getAllFacturas() {
        return ResponseEntity.ok(facturaServicio.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FacturaDTO> getFacturaById(@PathVariable int id) {
        FacturaDTO factura = facturaServicio.findById(id);
        if (factura == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(factura);
    }

    @PostMapping
    public ResponseEntity<FacturaDTO> createFactura(@RequestBody FacturaDTO request) {
        // Llama directamente a crearFactura(request) de tu servicio
        FacturaDTO nuevaFactura = facturaServicio.crearFactura(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaFactura);
    }
}