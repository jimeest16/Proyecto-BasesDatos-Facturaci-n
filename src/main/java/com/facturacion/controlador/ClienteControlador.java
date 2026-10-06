package com.facturacion.controlador;

import com.facturacion.dto.request.clienteDTORequest;
import com.facturacion.dto.response.clienteDTOResponse;
import com.facturacion.entidades.Cliente;
import com.facturacion.servicio.ClienteServicio;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping ("/clientes")
public class ClienteControlador {

    private final ClienteServicio clienteServicio;


    public ClienteControlador(ClienteServicio clienteServicio) {
        this.clienteServicio = clienteServicio;
    }

    @GetMapping()
    public ResponseEntity<List<clienteDTOResponse>> getAllClientes() {
        List<clienteDTOResponse> clientesEncontrado = clienteServicio.findAll();

        return ResponseEntity.ok(clientesEncontrado);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<clienteDTOResponse> getClienteByCodCliente(@PathVariable int id){
        clienteDTOResponse clienteEncontrado = clienteServicio.findByCodCliente(id);

        if (  clienteEncontrado == null) {
            // si no fue encontrado
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(clienteEncontrado);

    }

    //GUARDAR CON POST
    @PostMapping
    public ResponseEntity<clienteDTOResponse> createCliente(@RequestBody clienteDTORequest request) {
        clienteDTOResponse nuevoCliente = clienteServicio.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCliente);
    }
    
     //buscar CON POST
    @PostMapping("/buscar/nombre")
    public ResponseEntity<List<clienteDTOResponse>> getClientesByNombre(@RequestBody String nombre) {
       List<clienteDTOResponse> clients = clienteServicio.findByNomCliente(nombre);
        return ResponseEntity.ok(clients);
    }

     //buscar por estado CON POST
    @PostMapping("/buscar/estado")
    public ResponseEntity<List<clienteDTOResponse>> getClientesByEstado(@RequestBody String estado) {
       List<clienteDTOResponse> clients = clienteServicio.findByEstado(estado);
        return ResponseEntity.ok(clients);
    }
    

}

