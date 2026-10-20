package com.facturacion.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.facturacion.entidades.NotaCreditoDebito;

@Repository
public interface NotaCreditoDebitoRepo extends JpaRepository<NotaCreditoDebito, Integer> {
    NotaCreditoDebito findByCodNota(Integer codNota);
}