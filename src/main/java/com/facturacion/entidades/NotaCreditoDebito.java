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

    @Column(name = "fec_nota", nullable = false, insertable = false, updatable = false)
    private LocalDateTime fecNota;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "num_factura", nullable = false)
    private Factura factura;

    @Column(name = "ind_tipo", nullable = false, length = 10)
    private String indTipo;

    @Column(name = "des_motivo", nullable = false, length = 255)
    private String desMotivo;

    public NotaCreditoDebito() {}

    public Integer getCodNota() { return codNota; }
    public void setCodNota(Integer codNota) { this.codNota = codNota; }

    public LocalDateTime getFecNota() { return fecNota; }
    public void setFecNota(LocalDateTime fecNota) { this.fecNota = fecNota; }

    public Factura getFactura() { return factura; }
    public void setFactura(Factura factura) { this.factura = factura; }

    public String getIndTipo() { return indTipo; }
    public void setIndTipo(String indTipo) { this.indTipo = indTipo; }

    public String getDesMotivo() { return desMotivo; }
    public void setDesMotivo(String desMotivo) { this.desMotivo = desMotivo; }
}