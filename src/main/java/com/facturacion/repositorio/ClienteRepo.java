package com.facturacion.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.facturacion.entidades.Cliente;

@Repository
public interface ClienteRepo extends JpaRepository<Cliente, Integer> {

    Cliente findByCodCliente(Integer codCliente);

    List<Cliente> findByNomClienteContainingIgnoreCase(String nomCliente);

    List<Cliente> findByIndEstado(String indEstado);

    @Procedure(procedureName = "dbo.sp_InsertarCliente")
    Integer insertarCliente(
        @Param("nom_cliente") String nomCliente,
        @Param("tip_identificacion") String tipIdentificacion,
        @Param("num_identificacion_texto") String numIdentificacionTexto,
        @Param("num_telefono_texto") String numTelefonoTexto,
        @Param("des_correo_texto") String desCorreoTexto,
        @Param("des_provincia") String desProvincia,
        @Param("des_canton") String desCanton,
        @Param("des_distrito") String desDistrito,
        @Param("des_barrio") String desBarrio,
        @Param("des_profesion") String desProfesion,
        @Param("des_actividad_economica") String desActividadEconomica
    );
}