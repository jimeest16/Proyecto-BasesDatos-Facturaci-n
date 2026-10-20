package com.facturacion.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.facturacion.entidades.TiposImpuesto;
import java.util.List;

@Repository
public interface TiposImpuestoRepo extends JpaRepository<TiposImpuesto, String> {
    TiposImpuesto findByCodTipoImpuesto(String codTipoImpuesto);
    List<TiposImpuesto> findByIndEstado(String indEstado);
}