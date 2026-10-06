package com.facturacion.dto.request;

import lombok.Data;

@Data // inyecta los getters and setters
public class clienteDTORequest {

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

    public clienteDTORequest() {
    }

    public clienteDTORequest(String nomCliente, String tipIdentificacion, String numIdentificacion, String numTelefono,
            String desCorreo, String desProvincia, String desCanton, String desDistrito, String desBarrio,
            String desProfesion, String desActividadEconomica) {

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
    }

}