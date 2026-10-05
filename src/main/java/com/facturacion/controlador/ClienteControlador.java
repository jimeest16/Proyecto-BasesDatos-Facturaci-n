package com.facturacion.controlador;

import com.facturacion.entidades.Cliente;
import com.facturacion.servicio.ClienteServicio;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
@RequestMapping ("/clientes")
public class ClienteControlador {

    private final ClienteServicio clienteServicio;


    public ClienteControlador(ClienteServicio clienteServicio) {
        this.clienteServicio = clienteServicio;
    }
    @GetMapping()
    public ResponseEntity<List<Cliente>> getAllClientes() {
        List<Cliente> clientesEncontrado = clienteServicio.findAll();

        return ResponseEntity.ok(clientesEncontrado);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteByCodCliente(@PathVariable int id){

    
        Cliente clienteEncontrado = clienteServicio.findByCodCliente(id);

        if (  clienteEncontrado == null) {
            // si no fue encontrado
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(clienteEncontrado);

    }

}

