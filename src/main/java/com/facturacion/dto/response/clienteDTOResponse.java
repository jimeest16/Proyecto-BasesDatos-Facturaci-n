package com.facturacion.dto.response;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class clienteDTOResponse {
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

    private String mensaje ;


    public clienteDTOResponse(String mensaje) {
        this.mensaje = mensaje;
    
    }

    public clienteDTOResponse(Integer codCliente, String nomCliente, String tipIdentificacion, String numIdentificacion, String numTelefono,
            String desCorreo, String desProvincia, String desCanton, String desDistrito, String desBarrio,
            String desProfesion, String desActividadEconomica, LocalDateTime fecRegistro, String indEstado) {
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
        this.mensaje = "Cliente registrado correctamente";
    }

}
