package com.facturacion.entidades;

import jakarta.persistence.*;
import java.math.BigDecimal;


@Entity
@Table(name = "FacturasDetalle", schema = "dbo")
public class FacturaDetalle {

    @EmbeddedId
    private FacturaDetalleID id = new FacturaDetalleID();

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

    public FacturaDetalle() {}

    public FacturaDetalleID getId() { return id; }
    public void setId(FacturaDetalleID id) { this.id = id; }

    public Factura getFactura() { return factura; }
    public void setFactura(Factura factura) { this.factura = factura; }

    public ProductoServicio getProducto() { return producto; }
    public void setProducto(ProductoServicio producto) { this.producto = producto; }

    public BigDecimal getCantidad() { return cantidad; }
    public void setCantidad(BigDecimal cantidad) { this.cantidad = cantidad; }

    public BigDecimal getMontoLinea() { return montoLinea; }
    public void setMontoLinea(BigDecimal montoLinea) { this.montoLinea = montoLinea; }
}