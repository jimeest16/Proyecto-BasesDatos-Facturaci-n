package com.facturacion.entidades;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "ProductosServicios", schema = "dbo")

public class ProductoServicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_producto")
    private Integer codProducto;

    @Column(name = "cod_cabys", nullable = false, length = 20)
    private String codCabys;

    @Column(name = "des_producto", nullable = false, length = 255)
    private String desProducto;

    @Column(name = "unidad_medida", nullable = false, length = 20)
    private String unidadMedida;

    @Column(name = "precio", nullable = false, precision = 18, scale = 2)
    private BigDecimal precio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cod_tipo_impuesto", nullable = false)
    private TiposImpuesto tipoImpuesto;

    @Column(name = "ind_estado", nullable = false, length = 1)
    private String indEstado = "A";
}