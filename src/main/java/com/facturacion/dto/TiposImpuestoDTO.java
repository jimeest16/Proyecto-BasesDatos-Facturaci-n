package com.facturacion.dto;

import java.math.BigDecimal;

public class TiposImpuestoDTO {
    private String codTipoImpuesto;
    private String desTipoImpuesto;
    private BigDecimal porcentaje; // con big decimal redondea
    private BigDecimal tarifa; // con big decimal redondea
    private String indEstado;


public TiposImpuestoDTO() {}

    public TiposImpuestoDTO(String codTipoImpuesto, String desTipoImpuesto, BigDecimal porcentaje, BigDecimal tarifa, String indEstado) {
        this.codTipoImpuesto = codTipoImpuesto;
        this.desTipoImpuesto = desTipoImpuesto;
        this.porcentaje = porcentaje;
        this.tarifa = tarifa;
        this.indEstado = indEstado;
    }

    public String getCodTipoImpuesto() { return codTipoImpuesto; }
    public void setCodTipoImpuesto(String codTipoImpuesto) { this.codTipoImpuesto = codTipoImpuesto; }

    public String getDesTipoImpuesto() { return desTipoImpuesto; }
    public void setDesTipoImpuesto(String desTipoImpuesto) { this.desTipoImpuesto = desTipoImpuesto; }

    public BigDecimal getPorcentaje() { return porcentaje; }
    public void setPorcentaje(BigDecimal porcentaje) { this.porcentaje = porcentaje; }

    public BigDecimal getTarifa() { return tarifa; }
    public void setTarifa(BigDecimal tarifa) { this.tarifa = tarifa; }

    public String getIndEstado() { return indEstado; }
    public void setIndEstado(String indEstado) { this.indEstado = indEstado; }
}
