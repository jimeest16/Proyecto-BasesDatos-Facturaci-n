package com.facturacion.repositorio;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.facturacion.entidades.Cliente;


public interface ClienteRepo extends JpaRepository<Cliente, Integer> {
//aqui las firmas
List <Cliente> findByNomCliente(String nomCliente);

Cliente findByCodCliente ( int codCliente);

List <Cliente> findByIndEstado ( String indEstado);

    
}
