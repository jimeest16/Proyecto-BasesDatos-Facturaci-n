package com.facturacion.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.facturacion.entidades.ProductoServicio;
import java.util.List;

@Repository
public interface ProductoServicioRepo extends JpaRepository<ProductoServicio, Integer> {
    ProductoServicio findByCodProducto(Integer codProducto);
    List<ProductoServicio> findByDesProductoContainingIgnoreCase(String desProducto);
}