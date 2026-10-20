package com.facturacion.dto;

import java.time.LocalDateTime;

public class NotaCreditoDebitoDTO {
    private Integer codNota;
    private LocalDateTime fecNota;
    private Integer numFactura;
    private String indTipo; // Credito o Debito
    private String desMotivo;

    public NotaCreditoDebitoDTO() {}

    public NotaCreditoDebitoDTO(Integer codNota, LocalDateTime fecNota, Integer numFactura, String indTipo, String desMotivo) {
        this.codNota = codNota;
        this.fecNota = fecNota;
        this.numFactura = numFactura;
        this.indTipo = indTipo;
        this.desMotivo = desMotivo;
    }

    public Integer getCodNota() { return codNota; }
    public void setCodNota(Integer codNota) { this.codNota = codNota; }

    public LocalDateTime getFecNota() { return fecNota; }
    public void setFecNota(LocalDateTime fecNota) { this.fecNota = fecNota; }

    public Integer getNumFactura() { return numFactura; }
    public void setNumFactura(Integer numFactura) { this.numFactura = numFactura; }

    public String getIndTipo() { return indTipo; }
    public void setIndTipo(String indTipo) { this.indTipo = indTipo; }

    public String getDesMotivo() { return desMotivo; }
    public void setDesMotivo(String desMotivo) { this.desMotivo = desMotivo; }
}