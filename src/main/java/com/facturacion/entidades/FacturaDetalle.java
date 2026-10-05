package com.facturacion.entidades;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "FacturasDetalle", schema = "dbo")

public class FacturaDetalle {

    @EmbeddedId
    private FacturaDetallePK id = new FacturaDetallePK();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("numFactura")
    @JoinColumn(name = "num_factura")
    private Factura factura;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("codProducto")
    @JoinColumn(name = "cod_producto")
    private ProductoServicio producto;

    @Column(name = "cantidad", nullable = false, precision = 12, scale = 2)
    private BigDecimal cantidad;

    @Column(name = "monto_linea", nullable = false, precision = 18, scale = 2)
    private BigDecimal montoLinea;
}