package com.facturacion.controlador;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.facturacion.dto.TiposImpuestoDTO;
import com.facturacion.servicio.TiposImpuestoServicio;

@RestController
@RequestMapping("/api/tipos-impuesto")
public class TiposIpuestoControlador {

    private final TiposImpuestoServicio servicio;

    public TiposIpuestoControlador(TiposImpuestoServicio servicio) {
        this.servicio = servicio;
    }

    @GetMapping
    public ResponseEntity<List<TiposImpuestoDTO>> getAll() {
        return ResponseEntity.ok(servicio.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TiposImpuestoDTO> getById(@PathVariable String id) {
        TiposImpuestoDTO dto = servicio.findById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<TiposImpuestoDTO> create(@RequestBody TiposImpuestoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(servicio.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TiposImpuestoDTO> update(@PathVariable String id, @RequestBody TiposImpuestoDTO dto) {
        TiposImpuestoDTO actualizado = servicio.actualizar(id, dto);
        return actualizado != null ? ResponseEntity.ok(actualizado) : ResponseEntity.notFound().build();
    }

    // Borrado lógico: el tipo de impuesto queda Inactivo (I) porque lo usan productos.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        return servicio.inactivar(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}