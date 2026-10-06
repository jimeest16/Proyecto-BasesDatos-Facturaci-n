package com.facturacion.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "Clientes", schema = "dbo")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_cliente")
    private Integer codCliente;

    @Column(name = "nom_cliente", nullable = false, length = 100)
    private String nomCliente;

    @Column(name = "tip_identificacion", nullable = false, length = 20)
    private String tipIdentificacion;

    // Se mapean como byte[] porque en la BD son VARBINARY por el cifrado
    // cumple con el cell level encryption

    @Column(name = "num_identificacion", nullable = false, columnDefinition = "VARBINARY(MAX)")
    private byte[] numIdentificacion;

    @Column(name = "num_telefono", nullable = false, columnDefinition = "VARBINARY(MAX)")
    private byte[] numTelefono;

    @Column(name = "des_correo", nullable = false, columnDefinition = "VARBINARY(MAX)")
    private byte[] desCorreo;
    @Column(name = "des_provincia", nullable = false, length = 50)
    private String desProvincia;

    @Column(name = "des_canton", nullable = false, length = 50)
    private String desCanton;

    @Column(name = "des_distrito", nullable = false, length = 50)
    private String desDistrito;

    @Column(name = "des_barrio", length = 50)
    private String desBarrio;

    @Column(name = "des_profesion", nullable = false, length = 100)
    private String desProfesion;

    @Column(name = "des_actividad_economica", nullable = false, length = 100)
    private String desActividadEconomica;

    @Column(name = "fec_registro", insertable = false, updatable = false)
    private LocalDateTime fecRegistro;

    @Column(name = "ind_estado", nullable = false, length = 1)
    private String indEstado = "A";

    // IMPORTANTE LOS CONTRUCTORES, GETTERS Y SETTERS PARA QUE FUNCIONE EL JPA
    public Cliente() {
    }

    public Cliente(String nomCliente, String tipIdentificacion, byte[] numIdentificacion, byte[] numTelefono,
            byte[] desCorreo, String desProvincia, String desCanton, String desDistrito, String desBarrio,
            String desProfesion, String desActividadEconomica, LocalDateTime fecRegistro, String indEstado) {
        this.nomCliente = nomCliente;
        this.tipIdentificacion = tipIdentificacion;
        this.numIdentificacion = numIdentificacion;
        this.numTelefono = numTelefono;
        this.desCorreo = desCorreo;
        this.desProvincia = desProvincia;
        this.desCanton = desCanton;
        this.desDistrito = desDistrito;
        this.desBarrio = desBarrio;
        this.desProfesion = desProfesion;
        this.desActividadEconomica = desActividadEconomica;
        this.fecRegistro = fecRegistro;
        this.indEstado = indEstado;
    }

    public Integer getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(Integer codCliente) {
        this.codCliente = codCliente;
    }

    public String getNomCliente() {
        return nomCliente;
    }

    public void setNomCliente(String nomCliente) {
        this.nomCliente = nomCliente;
    }

    public String getTipIdentificacion() {
        return tipIdentificacion;
    }

    public void setTipIdentificacion(String tipIdentificacion) {
        this.tipIdentificacion = tipIdentificacion;
    }

    public byte[] getNumIdentificacion() {
        return numIdentificacion;
    }

    public void setNumIdentificacion(byte[] numIdentificacion) {
        this.numIdentificacion = numIdentificacion;
    }

    public byte[] getNumTelefono() {
        return numTelefono;
    }

    public void setNumTelefono(byte[] numTelefono) {
        this.numTelefono = numTelefono;
    }

    public byte[] getDesCorreo() {
        return desCorreo;
    }

    public void setDesCorreo(byte[] desCorreo) {
        this.desCorreo = desCorreo;
    }

    public String getDesProvincia() {
        return desProvincia;
    }

    public void setDesProvincia(String desProvincia) {
        this.desProvincia = desProvincia;
    }

    public String getDesCanton() {
        return desCanton;
    }

    public void setDesCanton(String desCanton) {
        this.desCanton = desCanton;
    }

    public String getDesDistrito() {
        return desDistrito;
    }

    public void setDesDistrito(String desDistrito) {
        this.desDistrito = desDistrito;
    }

    public String getDesBarrio() {
        return desBarrio;
    }

    public void setDesBarrio(String desBarrio) {
        this.desBarrio = desBarrio;
    }

    public String getDesProfesion() {
        return desProfesion;
    }

    public void setDesProfesion(String desProfesion) {
        this.desProfesion = desProfesion;
    }

    public String getDesActividadEconomica() {
        return desActividadEconomica;
    }

    public void setDesActividadEconomica(String desActividadEconomica) {
        this.desActividadEconomica = desActividadEconomica;
    }

    public LocalDateTime getFecRegistro() {
        return fecRegistro;
    }

    public void setFecRegistro(LocalDateTime fecRegistro) {
        this.fecRegistro = fecRegistro;
    }

    public String getIndEstado() {
        return indEstado;
    }

    public void setIndEstado(String indEstado) {
        this.indEstado = indEstado;
    }

}