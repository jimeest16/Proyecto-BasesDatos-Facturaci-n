package com.facturacion.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "TiposImpuesto", schema = "dbo")
public class TiposImpuesto {

    @Id
    @Column(name = "cod_tipo_impuesto", nullable = false, length = 10)
    private String codTipoImpuesto;

    @Column(name = "des_tipo_impuesto", nullable = false, length = 100)
    private String desTipoImpuesto;

    @Column(name = "porcentaje", nullable = false, precision = 5, scale = 2)
    private BigDecimal porcentaje;

    @Column(name = "tarifa", nullable = false, precision = 18, scale = 2)
    private BigDecimal tarifa;

    @Column(name = "ind_estado", nullable = false, length = 1)
    private String indEstado = "A";

    public TiposImpuesto() {}

    public TiposImpuesto(String codTipoImpuesto, String desTipoImpuesto, BigDecimal porcentaje, BigDecimal tarifa, String indEstado) {
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