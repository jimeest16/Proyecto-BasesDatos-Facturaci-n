package com.facturacion.servicio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameterValue;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.facturacion.dto.ClienteDTO;
import com.facturacion.repositorio.ClienteRepo;

/**
 * CRUD de clientes (REQ 002) usando procedimientos almacenados.
 * Los datos sensibles se cifran y descifran dentro de SQL Server con
 * LlaveSimetricaClientes (REQ 006); la aplicación nunca maneja la llave.
 *   - Insertar:   dbo.sp_InsertarCliente
 *   - Consultar:  dbo.sp_ConsultarClientes   (devuelve los datos descifrados)
 *   - Modificar:  dbo.sp_ActualizarCliente   (vuelve a cifrar los datos sensibles)
 *   - Eliminar:   dbo.sp_EliminarClienteSeguro (solo si el cliente no tiene facturas)
 */
@Service
@Transactional
public class ClienteServicio {

    private final ClienteRepo clienteRepo;
    private final JdbcTemplate jdbc;

    public ClienteServicio(ClienteRepo clienteRepo, JdbcTemplate jdbc) {
        this.clienteRepo = clienteRepo;
        this.jdbc = jdbc;
    }

    // ------------------------------------------------------------------ consultas

    @Transactional(readOnly = true)
    public List<ClienteDTO> findAll() {
        return consultar(null, null);
    }

    @Transactional(readOnly = true)
    public ClienteDTO findByCodCliente(int codCliente) {
        List<ClienteDTO> lista = consultar(codCliente, null);
        return lista.isEmpty() ? null : lista.get(0);
    }

    @Transactional(readOnly = true)
    public List<ClienteDTO> findByNomCliente(String nomCliente) {
        String nombre = nomCliente == null ? null : nomCliente.trim();
        return consultar(null, nombre);
    }

    @Transactional(readOnly = true)
    public List<ClienteDTO> findByEstado(String indEstado) {
        String estado = indEstado == null ? "" : indEstado.trim();
        return consultar(null, null).stream()
                .filter(c -> estado.equalsIgnoreCase(c.getIndEstado()))
                .collect(Collectors.toList());
    }

    private List<ClienteDTO> consultar(Integer cod, String nombre) {
        return jdbc.query(
            "EXEC dbo.sp_ConsultarClientes @cod_cliente = ?, @nom_cliente = ?",
            (rs, i) -> mapear(rs),
            new SqlParameterValue(Types.INTEGER, cod),
            new SqlParameterValue(Types.VARCHAR, nombre));
    }

    // ------------------------------------------------------------------ inserción

    public ClienteDTO save(ClienteDTO request) {
        Integer idGenerado = clienteRepo.insertarCliente(
            request.getNomCliente(),
            request.getTipIdentificacion(),
            request.getNumIdentificacion(),
            request.getNumTelefono(),
            request.getDesCorreo(),
            request.getDesProvincia(),
            request.getDesCanton(),
            request.getDesDistrito(),
            request.getDesBarrio() != null ? request.getDesBarrio() : "",
            request.getDesProfesion(),
            request.getDesActividadEconomica()
        );

        if (idGenerado == null) {
            List<ClienteDTO> encontrados = findByNomCliente(request.getNomCliente());
            return encontrados.isEmpty() ? request : encontrados.get(encontrados.size() - 1);
        }
        return findByCodCliente(idGenerado);
    }

    // ------------------------------------------------------------------ modificación

    public ClienteDTO actualizar(int id, ClienteDTO request) {
        ClienteDTO actual = findByCodCliente(id);
        if (actual == null) {
            return null;
        }
        // sp_ActualizarCliente vuelve a cifrar los tres datos sensibles: si alguno
        // llega vacío se conserva el valor actual (ya descifrado por la consulta).
        jdbc.update(
            "EXEC dbo.sp_ActualizarCliente ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?",
            new SqlParameterValue(Types.INTEGER, id),
            new SqlParameterValue(Types.VARCHAR, valor(request.getNomCliente(), actual.getNomCliente())),
            new SqlParameterValue(Types.VARCHAR, valor(request.getTipIdentificacion(), actual.getTipIdentificacion())),
            new SqlParameterValue(Types.VARCHAR, valor(request.getNumIdentificacion(), actual.getNumIdentificacion())),
            new SqlParameterValue(Types.VARCHAR, valor(request.getNumTelefono(), actual.getNumTelefono())),
            new SqlParameterValue(Types.VARCHAR, valor(request.getDesCorreo(), actual.getDesCorreo())),
            new SqlParameterValue(Types.VARCHAR, valor(request.getDesProvincia(), actual.getDesProvincia())),
            new SqlParameterValue(Types.VARCHAR, valor(request.getDesCanton(), actual.getDesCanton())),
            new SqlParameterValue(Types.VARCHAR, valor(request.getDesDistrito(), actual.getDesDistrito())),
            new SqlParameterValue(Types.VARCHAR, request.getDesBarrio() != null ? request.getDesBarrio().trim() : actual.getDesBarrio()),
            new SqlParameterValue(Types.VARCHAR, valor(request.getDesProfesion(), actual.getDesProfesion())),
            new SqlParameterValue(Types.VARCHAR, valor(request.getDesActividadEconomica(), actual.getDesActividadEconomica())),
            new SqlParameterValue(Types.CHAR, valor(request.getIndEstado(), actual.getIndEstado() != null ? actual.getIndEstado() : "A")));
        return findByCodCliente(id);
    }

    // ------------------------------------------------------------------ eliminación

    public boolean eliminar(int id) {
        if (findByCodCliente(id) == null) {
            return false;
        }
        try {
            jdbc.update("EXEC dbo.sp_EliminarClienteSeguro ?", new SqlParameterValue(Types.INTEGER, id));
            return true;
        } catch (DataAccessException e) {
            String mensaje = e.getMostSpecificCause().getMessage();
            if (mensaje != null && mensaje.contains("facturas")) {
                // Regla de negocio del procedimiento: un cliente con facturas no se borra, se inactiva.
                throw new IllegalStateException(mensaje);
            }
            throw e;
        }
    }

    // ------------------------------------------------------------------ utilidades

    private static String valor(String nuevo, String actual) {
        return (nuevo == null || nuevo.isBlank()) ? actual : nuevo.trim();
    }

    private ClienteDTO mapear(ResultSet rs) throws SQLException {
        Timestamp fecha = rs.getTimestamp("fec_registro");
        return new ClienteDTO(
            rs.getInt("cod_cliente"),
            rs.getString("nom_cliente"),
            rs.getString("tip_identificacion"),
            rs.getString("num_identificacion"),
            rs.getString("num_telefono"),
            rs.getString("des_correo"),
            rs.getString("des_provincia"),
            rs.getString("des_canton"),
            rs.getString("des_distrito"),
            rs.getString("des_barrio"),
            rs.getString("des_profesion"),
            rs.getString("des_actividad_economica"),
            fecha != null ? fecha.toLocalDateTime() : null,
            rs.getString("ind_estado"));
    }
}