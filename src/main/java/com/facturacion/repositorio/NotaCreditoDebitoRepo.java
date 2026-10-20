package com.facturacion.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.facturacion.entidades.NotaCreditoDebito;

@Repository
public interface NotaCreditoDebitoRepo extends JpaRepository<NotaCreditoDebito, Integer> {

    @Procedure(procedureName = "sp_InsertarNota")
    Integer spInsertarNota(
        @Param("num_factura") Integer numFactura,
        @Param("ind_tipo") String indTipo,
        @Param("des_motivo") String desMotivo
    );
}