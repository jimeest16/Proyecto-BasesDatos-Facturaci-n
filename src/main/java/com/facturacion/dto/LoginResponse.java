
package com.facturacion.dto;

public class LoginResponse {

    private boolean autenticado;
    private String rol;
    private String mensaje;

    public LoginResponse(boolean autenticado, String rol, String mensaje) {
        this.autenticado = autenticado;
        this.rol = rol;
        this.mensaje = mensaje;
    }

    public boolean isAutenticado() {
        return autenticado;
    }

    public String getRol() {
        return rol;
    }

    public String getMensaje() {
        return mensaje;
    }
}
