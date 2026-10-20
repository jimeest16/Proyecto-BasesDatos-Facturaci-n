
package com.facturacion.servicio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final String url =
        "jdbc:sqlserver://127.0.0.1:1433;"
        + "databaseName=SistemaFacturacionEmpresaPropi;"
        + "encrypt=true;"
        + "trustServerCertificate=true;"
        + "loginTimeout=5";

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
}
