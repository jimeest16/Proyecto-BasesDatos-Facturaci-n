package com.facturacion.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
// es serializable porque es una clave primaria compuesta y JPA requiere que las claves primarias sean serializables
// es decir que es serializable para que pueda ser convertido a un flujo de bytes y almacenado o transmitido
public class FacturaDetallePK implements Serializable {

    @Column(name = "num_factura")
    private Integer numFactura;

    @Column(name = "cod_producto")
    private Integer codProducto;
}