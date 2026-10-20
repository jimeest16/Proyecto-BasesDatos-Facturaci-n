package com.facturacion.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.facturacion.entidades.Factura;
import java.math.BigDecimal;

@Repository
public interface FacturaRepo extends JpaRepository<Factura, Integer> {
    Factura findByNumFactura(Integer numFactura);

    // Invocación explícita del Stored Procedure requerido por el REQ 002
    @Procedure(procedureName = "dbo.sp_InsertarFactura")
    Integer spInsertarFactura(
        @Param("cod_cliente") Integer codCliente,
        @Param("condicion_venta") String condicionVenta,
        @Param("medio_pago") String medioPago,
        @Param("moneda") String moneda,
        @Param("tipo_cambio") BigDecimal tipoCambio,
        @Param("observaciones") String observaciones,
        @Param("subtotal") BigDecimal subtotal,
        @Param("total_impuestos") BigDecimal totalImpuestos,
        @Param("monto_total") BigDecimal montoTotal
    );
}