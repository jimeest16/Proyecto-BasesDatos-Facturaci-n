package com.facturacion.entidades;



import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "BitacoraAuditoria", schema = "dbo")

public class BitacoraAuditoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_bitacora")
    private Integer codBitacora;

    @Column(name = "fec_registro", insertable = false, updatable = false)
    private LocalDateTime fecRegistro;

    @Column(name = "cod_guid", length = 255)
    private String codGuid;

    @Column(name = "ind_accion", nullable = false, length = 1)
    private String indAccion;

    @Column(name = "cod_usuario", nullable = false, length = 128)
    private String codUsuario;

    @Column(name = "cod_sesion_usuario")
    private Integer codSesionUsuario;

    @Column(name = "cod_tabla", nullable = false, length = 255)
    private String codTabla;

    @Column(name = "cod_llave", length = 2000)
    private String codLlave;

    @Column(name = "des_datos", columnDefinition = "VARCHAR(MAX)")
    private String desDatos;
}