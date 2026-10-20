package com.facturacion.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class FacturaDTO {
    private Integer numFactura;
    private Integer codCliente;
    private String nomCliente;
    private LocalDateTime fecFactura;
    private String condicionVenta;
    private String medioPago;
    private String moneda;
    private BigDecimal tipoCambio;
    private String observaciones;
    private BigDecimal subtotal;
    private BigDecimal totalImpuestos;
    private BigDecimal montoTotal;
    private List<FacturaDetalleDTO> detalles;

    public FacturaDTO() {}

    public Integer getNumFactura() { return numFactura; }
    public void setNumFactura(Integer numFactura) { this.numFactura = numFactura; }

    public Integer getCodCliente() { return codCliente; }
    public void setCodCliente(Integer codCliente) { this.codCliente = codCliente; }

    public String getNomCliente() { return nomCliente; }
    public void setNomCliente(String nomCliente) { this.nomCliente = nomCliente; }

    public LocalDateTime getFecFactura() { return fecFactura; }
    public void setFecFactura(LocalDateTime fecFactura) { this.fecFactura = fecFactura; }

    public String getCondicionVenta() { return condicionVenta; }
    public void setCondicionVenta(String condicionVenta) { this.condicionVenta = condicionVenta; }

    public String getMedioPago() { return medioPago; }
    public void setMedioPago(String medioPago) { this.medioPago = medioPago; }

    public String getMoneda() { return moneda; }
    public void setMoneda(String moneda) { this.moneda = moneda; }

    public BigDecimal getTipoCambio() { return tipoCambio; }
    public void setTipoCambio(BigDecimal tipoCambio) { this.tipoCambio = tipoCambio; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }

    public BigDecimal getSubtotal() { return subtotal; }
    public void setSubtotal(BigDecimal subtotal) { this.subtotal = subtotal; }

    public BigDecimal getTotalImpuestos() { return totalImpuestos; }
    public void setTotalImpuestos(BigDecimal totalImpuestos) { this.totalImpuestos = totalImpuestos; }

    public BigDecimal getMontoTotal() { return montoTotal; }
    public void setMontoTotal(BigDecimal montoTotal) { this.montoTotal = montoTotal; }

    public List<FacturaDetalleDTO> getDetalles() { return detalles; }
    public void setDetalles(List<FacturaDetalleDTO> detalles) { this.detalles = detalles; }
}
