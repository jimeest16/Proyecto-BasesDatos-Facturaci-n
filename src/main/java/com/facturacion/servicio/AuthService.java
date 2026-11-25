
package com.facturacion.servicio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.CallableStatement;
import java.sql.Types;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

@Service
public class AuthService {

    @Value("${spring.datasource.url}")
    private String url;

    public String iniciarSesion(String usuario, String contrasena) {

        if (usuario == null || usuario.isBlank()
                || contrasena == null || contrasena.isBlank()) {
            return null;
        }

        String sql =
            "SELECT " +
            "IS_MEMBER('Rol_Admin') AS administrador, " +
            "IS_MEMBER('Rol_Digitador') AS digitador, " +
            "IS_MEMBER('Rol_Consulta') AS consulta";

        try (Connection conexion =
                 DriverManager.getConnection(url, usuario, contrasena);
             PreparedStatement consulta =
                 conexion.prepareStatement(sql);
             ResultSet resultado = consulta.executeQuery()) {

            if (resultado.next()) {

                if (resultado.getInt("administrador") == 1) {
                    return "Rol_Admin";
                }

                if (resultado.getInt("digitador") == 1) {
                    return "Rol_Digitador";
                }

                if (resultado.getInt("consulta") == 1) {
                    return "Rol_Consulta";
                }
            }

            return null;

        } catch (SQLException e) {
          
            System.err.println(
                "Error de autenticacion SQL Server. Codigo: "
                + e.getErrorCode()
            );

            return null;
        }
    }

        // REQ 003: registra el ingreso en BitacoraSesiones y devuelve el código de la sesión.
    public Integer registrarInicioSesion(String usuario, String contrasena,
                                         String terminal, String ip) {
        try (Connection conexion = DriverManager.getConnection(url, usuario, contrasena);
             CallableStatement cs = conexion.prepareCall(
                 "{call dbo.sp_RegistrarInicioSesion(?, ?, ?, ?)}")) {
            cs.setString(1, usuario);
            cs.setString(2, terminal);
            cs.setString(3, ip);
            cs.registerOutParameter(4, Types.INTEGER);   
            cs.execute();
            return cs.getInt(4);
        } catch (SQLException e) {
            System.err.println("No se pudo registrar el inicio de sesión. Código: " + e.getErrorCode());
            return null;
        }
    }

    // Registra la fecha y hora de salida de esa sesión.
    public void registrarSalidaSesion(String usuario, String contrasena, Integer codSesion) {
        if (codSesion == null) {
            return;
        }
        try (Connection conexion = DriverManager.getConnection(url, usuario, contrasena);
             CallableStatement cs = conexion.prepareCall("{call dbo.sp_RegistrarSalidaSesion(?)}")) {
            cs.setInt(1, codSesion);
            cs.execute();
        } catch (SQLException e) {
            System.err.println("No se pudo registrar la salida de sesión. Código: " + e.getErrorCode());
        }
    }
}
