package com.facturacion.dto;

import java.time.LocalDateTime;

public class ClienteDTO {

    private Integer codCliente;
    private String nomCliente;
    private String tipIdentificacion;
    private String numIdentificacion;
    private String numTelefono;
    private String desCorreo;
    private String desProvincia;
    private String desCanton;
    private String desDistrito;
    private String desBarrio;
    private String desProfesion;
    private String desActividadEconomica;
    private LocalDateTime fecRegistro;
    private String indEstado;

    // Constructores...

  
    public Integer getCodCliente() { return codCliente; }
    public ClienteDTO(Integer codCliente, String nomCliente, String tipIdentificacion, String numIdentificacion,
            String numTelefono, String desCorreo, String desProvincia, String desCanton, String desDistrito,
            String desBarrio, String desProfesion, String desActividadEconomica, LocalDateTime fecRegistro,
            String indEstado) {


        this.codCliente = codCliente;
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
    public void setCodCliente(Integer codCliente) { this.codCliente = codCliente; }

    public String getNomCliente() { return nomCliente; }
    public void setNomCliente(String nomCliente) { this.nomCliente = nomCliente; }

    public String getTipIdentificacion() { return tipIdentificacion; }
    public void setTipIdentificacion(String tipIdentificacion) { this.tipIdentificacion = tipIdentificacion; }

    public String getNumIdentificacion() { return numIdentificacion; }
    public void setNumIdentificacion(String numIdentificacion) { this.numIdentificacion = numIdentificacion; }

    public String getNumTelefono() { return numTelefono; }
    public void setNumTelefono(String numTelefono) { this.numTelefono = numTelefono; }

    public String getDesCorreo() { return desCorreo; }
    public void setDesCorreo(String desCorreo) { this.desCorreo = desCorreo; }

    public String getDesProvincia() { return desProvincia; }
    public void setDesProvincia(String desProvincia) { this.desProvincia = desProvincia; }

    public String getDesCanton() { return desCanton; }
    public void setDesCanton(String desCanton) { this.desCanton = desCanton; }

    public String getDesDistrito() { return desDistrito; }
    public void setDesDistrito(String desDistrito) { this.desDistrito = desDistrito; }

    public String getDesBarrio() { return desBarrio; }
    public void setDesBarrio(String desBarrio) { this.desBarrio = desBarrio; }

    public String getDesProfesion() { return desProfesion; }
    public void setDesProfesion(String desProfesion) { this.desProfesion = desProfesion; }

    public String getDesActividadEconomica() { return desActividadEconomica; }
    public void setDesActividadEconomica(String desActividadEconomica) { this.desActividadEconomica = desActividadEconomica; }

    public LocalDateTime getFecRegistro() { return fecRegistro; }
    public void setFecRegistro(LocalDateTime fecRegistro) { this.fecRegistro = fecRegistro; }

    public String getIndEstado() { return indEstado; }
    public void setIndEstado(String indEstado) { this.indEstado = indEstado; }
}