package com.facturacion.entidades;

import java.io.Serializable;
import java.util.Objects;
import jakarta.persistence.Embeddable;

@Embeddable
//eston significa que trae dos llaves primarias una de producto y otra de factura

public class FacturaDetalleID implements Serializable {

    private Integer numFactura;
    private Integer codProducto;

    public FacturaDetalleID() {}

    public FacturaDetalleID(Integer numFactura, Integer codProducto) {
        this.numFactura = numFactura;
        this.codProducto = codProducto;
    }

    public Integer getNumFactura() { return numFactura; }
    public void setNumFactura(Integer numFactura) { this.numFactura = numFactura; }

    public Integer getCodProducto() { return codProducto; }
    public void setCodProducto(Integer codProducto) { this.codProducto = codProducto; }

    // metodos que si o si se necesitan
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FacturaDetalleID)) return false;
        FacturaDetalleID that = (FacturaDetalleID ) o;
        return Objects.equals(numFactura, that.numFactura) && Objects.equals(codProducto, that.codProducto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numFactura, codProducto);
    }
}