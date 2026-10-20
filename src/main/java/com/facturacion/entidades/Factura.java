package com.facturacion.entidades;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Facturas", schema = "dbo")
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "num_factura")
    private Integer numFactura;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cod_cliente", nullable = false)
    private Cliente cliente;

    @Column(name = "fec_factura", insertable = false, updatable = false)
    private LocalDateTime fecFactura;

    @Column(name = "condicion_venta", nullable = false, length = 20)
    private String condicionVenta;

    @Column(name = "medio_pago", nullable = false, length = 30)
    private String medioPago;

    @Column(name = "moneda", nullable = false, length = 5)
    private String moneda = "CRC";

    @Column(name = "tipo_cambio", nullable = false, precision = 10, scale = 4)
    private BigDecimal tipoCambio = BigDecimal.ONE;

    @Column(name = "observaciones", length = 500)
    private String observaciones;

    @Column(name = "subtotal", nullable = false, precision = 18, scale = 2)
    private BigDecimal subtotal;

    @Column(name = "total_impuestos", nullable = false, precision = 18, scale = 2)
    private BigDecimal totalImpuestos;

    @Column(name = "monto_total", nullable = false, precision = 18, scale = 2)
    private BigDecimal montoTotal;

    @OneToMany(mappedBy = "factura", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FacturaDetalle> detalles = new ArrayList<>();

    public Factura() {}

    public Integer getNumFactura() { return numFactura; }
    public void setNumFactura(Integer numFactura) { this.numFactura = numFactura; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

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

    public List<FacturaDetalle> getDetalles() { return detalles; }
    public void setDetalles(List<FacturaDetalle> detalles) { this.detalles = detalles; }
}