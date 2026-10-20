package com.facturacion.dto;

import java.math.BigDecimal;

public class FacturaDetalleDTO {
    private Integer codProducto;
    private String desProducto;
    private BigDecimal cantidad;
    private BigDecimal montoLinea;

    public FacturaDetalleDTO() {}

    public FacturaDetalleDTO(Integer codProducto, String desProducto, BigDecimal cantidad, BigDecimal montoLinea) {
        this.codProducto = codProducto;
        this.desProducto = desProducto;
        this.cantidad = cantidad;
        this.montoLinea = montoLinea;
    }

    public Integer getCodProducto() { return codProducto; }
    public void setCodProducto(Integer codProducto) { this.codProducto = codProducto; }

    public String getDesProducto() { return desProducto; }
    public void setDesProducto(String desProducto) { this.desProducto = desProducto; }

    public BigDecimal getCantidad() { return cantidad; }
    public void setCantidad(BigDecimal cantidad) { this.cantidad = cantidad; }

    public BigDecimal getMontoLinea() { return montoLinea; }
    public void setMontoLinea(BigDecimal montoLinea) { this.montoLinea = montoLinea; }
}