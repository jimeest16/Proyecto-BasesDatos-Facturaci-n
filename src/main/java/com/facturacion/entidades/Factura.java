package com.facturacion.entidades;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity 
@Table (name = "Facturas", schema = "dbo")

public class Factura {
    
    @Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "num_factura")
    private Integer numFactura;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "cod_cliente", nullable = false)
    private Cliente codCliente;

    @Column ( name = "fec_factura",insertable = false, updatable = false)
    private LocalDate fecFactura;

    @Column ( name = "condicion_venta", nullable = false, length = 20)
    private String condicionVenta;

    @Column ( name = "medio_pago", nullable = false, length = 30)
    private String medioPago;

    @Column ( name = "moneda", nullable = false, length = 5)
    private String moneda = "CRC";

    @Column ( name = "tipo_cambio", nullable = false, precision = 10, scale = 4)
    private BigDecimal tipoCambio = BigDecimal.ONE; // Por defecto es 1 para CRC

    @Column ( name = "observaciones", length = 500)
    private String observaciones;

    @Column (name = "subtotal", nullable = false, precision = 18, scale = 2)
    private BigDecimal subtotal;    

    @Column (name = "total_impuesto", nullable = false, precision = 18, scale = 2)
    private BigDecimal totalImpuesto;

    @Column ( name = "monto_total", nullable = false, precision = 18, scale = 2)
    private BigDecimal montoTotal;

    // lista de one a muchos de factura detalle
    @OneToMany(mappedBy = "factura", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FacturaDetalle> detalles = new ArrayList<>();
}
