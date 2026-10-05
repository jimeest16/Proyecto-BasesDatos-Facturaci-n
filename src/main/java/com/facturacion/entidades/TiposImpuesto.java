package com.facturacion.entidades;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Entity
@Table(name = "TiposImpuesto", schema = "dbo")
@Data
public class TiposImpuesto {

    @Id
    @Column(name = "cod_tipo_impuesto", length = 10)
    private String codTipoImpuesto;

    @Column(name = "des_tipo_impuesto", nullable = false, length = 100)
    private String desTipoImpuesto;

    @Column(name = "porcentaje", nullable = false, precision = 5, scale = 2)
    private BigDecimal porcentaje;

    @Column(name = "tarifa", nullable = false, precision = 18, scale = 2)
    private BigDecimal tarifa;

    @Column(name = "ind_estado", nullable = false, length = 1)
    private String indEstado = "A";
}
// use big decimal porque redondea exactamente y es más preciso para cálculos financieros