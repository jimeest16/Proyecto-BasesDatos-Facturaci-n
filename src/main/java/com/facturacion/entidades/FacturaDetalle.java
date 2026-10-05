package com.facturacion.entidades;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "FacturasDetalle", schema = "dbo")

public class FacturaDetalle implements Serializable {
// es serializable porque tiene una clave compuesta, que es la combinación de num_factura y cod_producto
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "num_factura")
    private Factura factura;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cod_producto")
    private ProductoServicio producto;

    @Column(name = "cantidad", nullable = false, precision = 12, scale = 2)
    private BigDecimal cantidad;

    @Column(name = "monto_linea", nullable = false, precision = 18, scale = 2)
    private BigDecimal montoLinea;

    // --- Constructores ---
    public FacturaDetalle() {
    }

    public FacturaDetalle(Factura factura, ProductoServicio producto) {
        this.factura = factura;
        this.producto = producto;
    }

    // --- Getters y Setters ---
    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public ProductoServicio getProducto() {
        return producto;
    }

    public void setProducto(ProductoServicio producto) {
        this.producto = producto;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getMontoLinea() {
        return montoLinea;
    }

    public void setMontoLinea(BigDecimal montoLinea) {
        this.montoLinea = montoLinea;
    }

    // por aquello
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FacturaDetalle that = (FacturaDetalle) o;
        return Objects.equals(factura, that.factura) &&
               Objects.equals(producto, that.producto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(factura, producto);
    }
}