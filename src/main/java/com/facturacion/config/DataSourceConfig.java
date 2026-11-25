package com.facturacion.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.UserCredentialsDataSourceAdapter;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

/**
 * REQ 003 / REQ 004 / REQ 005 / REQ 008
 * Cada operación se ejecuta en SQL Server con el login del usuario que inició
 * sesión (usr_admin, usr_digitador o usr_consulta). Así los permisos GRANT/DENY
 * los aplica el motor y los triggers de auditoría registran al usuario real.
 * Las credenciales de application.properties solo se usan al arrancar.
 */
@Configuration
public class DataSourceConfig {

    @Bean
    @Primary
    public UserCredentialsDataSourceAdapter dataSource(
            @Value("${spring.datasource.url}") String url,
            @Value("${spring.datasource.username}") String usuarioPorDefecto,
            @Value("${spring.datasource.password}") String clavePorDefecto) {

        SQLServerDataSource sqlServer = new SQLServerDataSource();
        sqlServer.setURL(url);

        UserCredentialsDataSourceAdapter adaptador = new UserCredentialsDataSourceAdapter();
        adaptador.setTargetDataSource(sqlServer);
        adaptador.setUsername(usuarioPorDefecto);
        adaptador.setPassword(clavePorDefecto);
        return adaptador;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}