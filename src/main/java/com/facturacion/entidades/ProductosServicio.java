package com.facturacion.entidades;

import java.math.BigDecimal;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity 
@Table (name = "ProductosServicios", schema = "dbo")

public class ProductosServicio {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "cod_producto")
    private String codProducto;

    @Column ( name = "cod_cabys", nullable = false, length = 20)
    private String codCabys;

    @Column ( name = "des_producto", nullable = false, length = 255)
    private String desProducto;

    @Column ( name = "unidad_medida", nullable = false, length = 20)
    private String unidadMedida;

    @Column ( name = "precio", nullable = false, precision = 18, scale = 2)
    private BigDecimal precio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn ( name = "cod_tipo_impuesto", nullable = false)
    private TiposImpuesto codTipoImpuesto;

    @Column ( name = "ind_estado", nullable = false, length = 1)
    private String indEstado = "A";
}
