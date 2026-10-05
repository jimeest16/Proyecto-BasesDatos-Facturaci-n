package com.facturacion.entidades;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "NotasCreditoDebito", schema = "dbo")

public class NotaCreditoDebito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_nota")
    private Integer codNota;

    @Column(name = "fec_nota", insertable = false, updatable = false)
    private LocalDateTime fecNota;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "num_factura", nullable = false)
    private Factura factura;

    @Column(name = "ind_tipo", nullable = false, length = 10)
    private String indTipo;

    @Column(name = "des_motivo", nullable = false, length = 255)
    private String desMotivo;
}