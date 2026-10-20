package com.facturacion.controlador;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.facturacion.dto.ProductoServicioDTO;
import com.facturacion.servicio.ProductoServicioServicio;

@RestController
@RequestMapping("/productos")
public class ProductosServicioControlador {

    private final ProductoServicioServicio servicio;

    public ProductosServicioControlador(ProductoServicioServicio servicio) {
        this.servicio = servicio;
    }

    @GetMapping
    public ResponseEntity<List<ProductoServicioDTO>> getAll() {
        return ResponseEntity.ok(servicio.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoServicioDTO> getById(@PathVariable int id) {
        ProductoServicioDTO dto = servicio.findById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ProductoServicioDTO> create(@RequestBody ProductoServicioDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(servicio.save(dto));
    }
}