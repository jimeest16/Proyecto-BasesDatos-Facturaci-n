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

    @ManyToOne(fetch = FetchType.LAZY) // muchos productos pueden tener un tipo de impuesto
    @JoinColumn(name = "cod_tipo_impuesto", nullable = false)
    private TiposImpuesto tipoImpuesto;

    @Column(name = "ind_estado", nullable = false, length = 1)
    private String indEstado = "A";

    public ProductoServicio() {}

    public Integer getCodProducto() { return codProducto; }
    public void setCodProducto(Integer codProducto) { this.codProducto = codProducto; }

    public String getCodCabys() { return codCabys; }
    public void setCodCabys(String codCabys) { this.codCabys = codCabys; }

    public String getDesProducto() { return desProducto; }
    public void setDesProducto(String desProducto) { this.desProducto = desProducto; }

    public String getUnidadMedida() { return unidadMedida; }
    public void setUnidadMedida(String unidadMedida) { this.unidadMedida = unidadMedida; }

    public BigDecimal getPrecio() { return precio; }
    public void setPrecio(BigDecimal precio) { this.precio = precio; }

    public TiposImpuesto getTipoImpuesto() { return tipoImpuesto; }
    public void setTipoImpuesto(TiposImpuesto tipoImpuesto) { this.tipoImpuesto = tipoImpuesto; }

    public String getIndEstado() { return indEstado; }
    public void setIndEstado(String indEstado) { this.indEstado = indEstado; }
}