package com.facturacion.entidades;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "BitacoraSesiones", schema = "dbo")

public class BitacoraSesion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_sesion")
    private Integer codSesion;

    @Column(name = "nom_usuario", nullable = false, length = 128)
    private String nomUsuario;

    @Column(name = "nom_terminal", nullable = false, length = 128)
    private String nomTerminal;

    @Column(name = "dir_ip", nullable = false, length = 45)
    private String dirIp;

    @Column(name = "fec_ingreso", insertable = false, updatable = false)
    private LocalDateTime fecIngreso;

    @Column(name = "fec_salida")
    private LocalDateTime fecSalida;

    @Column(name = "fec_registro", insertable = false, updatable = false)
    private LocalDateTime fecRegistro;
}