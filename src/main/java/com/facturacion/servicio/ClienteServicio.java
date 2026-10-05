package com.facturacion.servicio;

import java.util.List;
import org.springframework.stereotype.Service;

import com.facturacion.entidades.Cliente;
import com.facturacion.repositorio.ClienteRepo;

import jakarta.transaction.Transactional;

@Service 
@Transactional 
public class ClienteServicio {
    // aqui con las validaciones tambien

    private final ClienteRepo clienteRepo;

public ClienteServicio(ClienteRepo clienteRepo) {
        this.clienteRepo = clienteRepo;
    }

public List <Cliente> findByNomCliente(String nomCliente){
    return clienteRepo.findByNomCliente(nomCliente);
    
}
public Cliente findByCodCliente ( int codCliente){
    return clienteRepo.findByCodCliente(codCliente);
}

public List <Cliente> findByEstado ( String indEstado){
    return clienteRepo.findByIndEstado(indEstado);
}

public List <Cliente> findAll(){
    return clienteRepo.findAll();
}
}
