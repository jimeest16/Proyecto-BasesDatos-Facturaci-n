package com.facturacion.controlador;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.facturacion.dto.ClienteDTO;
import com.facturacion.servicio.ClienteServicio;

@RestController
@CrossOrigin(origins = "*") // Permite peticiones locales
@RequestMapping("/api/clientes") 
public class ClienteControlador {

    private final ClienteServicio clienteServicio;

    public ClienteControlador(ClienteServicio clienteServicio) {
        this.clienteServicio = clienteServicio;
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> getAllClientes() {
        return ResponseEntity.ok(clienteServicio.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> getClienteByCodCliente(@PathVariable int id) {
        ClienteDTO clienteEncontrado = clienteServicio.findByCodCliente(id);
        if (clienteEncontrado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(clienteEncontrado);
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> createCliente(@RequestBody ClienteDTO request) {
        ClienteDTO nuevoCliente = clienteServicio.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> updateCliente(@PathVariable int id, @RequestBody ClienteDTO request) {
        ClienteDTO clienteActualizado = clienteServicio.actualizar(id, request);
        if (clienteActualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(clienteActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable int id) {
        boolean eliminado = clienteServicio.eliminar(id);
        if (!eliminado) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/buscar/nombre")
    public ResponseEntity<List<ClienteDTO>> getClientesByNombre(@RequestBody String nombre) {
        return ResponseEntity.ok(clienteServicio.findByNomCliente(nombre));
    }

    @PostMapping("/buscar/estado")
    public ResponseEntity<List<ClienteDTO>> getClientesByEstado(@RequestBody String estado) {
        return ResponseEntity.ok(clienteServicio.findByEstado(estado));
    }
}