package com.facturacion.servicio;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ReporteServicio {

    @Value("${spring.datasource.url}")
    private String url;

    public List<Map<String, Object>> ejecutar(String nombre, String usuarioSql,
                                              String clave, Map<String, String> p) throws SQLException {
        String llamada;
        switch (nombre) {
            case "clientes":  llamada = "{call dbo.sp_ReporteClientes(?,?,?)}"; break;
            case "facturas":  llamada = "{call dbo.sp_ReporteFacturas(?,?)}"; break;
            case "auditoria": llamada = "{call dbo.sp_ReporteAuditoria(?,?,?)}"; break;
            case "seguridad": llamada = "{call dbo.sp_ReporteConfiguracionSeguridad(?)}"; break;
            default: throw new IllegalArgumentException("Reporte no válido.");
        }

        try (Connection cn = DriverManager.getConnection(url, usuarioSql, clave);
             CallableStatement cs = cn.prepareCall(llamada)) {

            switch (nombre) {
                case "clientes":
                    cs.setDate(1, fecha(p.get("desde"), "desde"));
                    cs.setDate(2, fecha(p.get("hasta"), "hasta"));
                    cs.setString(3, estado(p.get("estado")));
                    break;
                case "facturas":
                    cs.setDate(1, fecha(p.get("desde"), "desde"));
                    cs.setDate(2, fecha(p.get("hasta"), "hasta"));
                    break;
                case "auditoria":
                    cs.setDate(1, fecha(p.get("desde"), "desde"));
                    cs.setDate(2, fecha(p.get("hasta"), "hasta"));
                    textoOpcional(cs, 3, p.get("usuario"));
                    break;
                default: // seguridad
                    textoOpcional(cs, 1, p.get("usuario"));
            }

            boolean hayResultado = cs.execute();
            while (!hayResultado && cs.getUpdateCount() != -1) {
                hayResultado = cs.getMoreResults();
            }

            List<Map<String, Object>> filas = new ArrayList<>();
            if (hayResultado) {
                try (ResultSet rs = cs.getResultSet()) {
                    ResultSetMetaData md = rs.getMetaData();
                    int columnas = md.getColumnCount();
                    while (rs.next()) {
                        Map<String, Object> fila = new LinkedHashMap<>();
                        for (int i = 1; i <= columnas; i++) {
                            Object v = rs.getObject(i);
                            if (v instanceof Timestamp) {
                                v = ((Timestamp) v).toLocalDateTime().toString().replace('T', ' ');
                            }
                            fila.put(md.getColumnLabel(i), v);
                        }
                        filas.add(fila);
                    }
                }
            }
            return filas;
        }
    }

    private java.sql.Date fecha(String valor, String campo) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException("Falta la fecha " + campo + ".");
        }
        return java.sql.Date.valueOf(LocalDate.parse(valor.trim()));
    }

    private String estado(String valor) {
        return ("A".equals(valor) || "I".equals(valor)) ? valor : "T";
    }

    private void textoOpcional(CallableStatement cs, int pos, String valor) throws SQLException {
        if (valor == null || valor.isBlank()) {
            cs.setNull(pos, Types.VARCHAR);
        } else {
            cs.setString(pos, valor.trim());
        }
    }
}