package com.facturacion.dto;

import java.math.BigDecimal;

public class ProductoServicioDTO {
    private Integer codProducto;
    private String codCabys;
    private String desProducto;
    private String unidadMedida;
    private BigDecimal precio;
    private String codTipoImpuesto;
    private String indEstado;

    public ProductoServicioDTO() {}

    public ProductoServicioDTO(Integer codProducto, String codCabys, String desProducto, String unidadMedida, BigDecimal precio, String codTipoImpuesto, String indEstado) {
        this.codProducto = codProducto;
        this.codCabys = codCabys;
        this.desProducto = desProducto;
        this.unidadMedida = unidadMedida;
        this.precio = precio;
        this.codTipoImpuesto = codTipoImpuesto;
        this.indEstado = indEstado;
    }

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

    public String getCodTipoImpuesto() { return codTipoImpuesto; }
    public void setCodTipoImpuesto(String codTipoImpuesto) { this.codTipoImpuesto = codTipoImpuesto; }

    public String getIndEstado() { return indEstado; }
    public void setIndEstado(String indEstado) { this.indEstado = indEstado; }
}