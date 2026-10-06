package com.facturacion.servicio;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.facturacion.dto.request.clienteDTORequest;
import com.facturacion.dto.response.clienteDTOResponse;
import com.facturacion.entidades.Cliente;
import com.facturacion.repositorio.ClienteRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ClienteServicio {

    private final ClienteRepo clienteRepo;

    public ClienteServicio(ClienteRepo clienteRepo) {
        this.clienteRepo = clienteRepo;
    }


    public List<clienteDTOResponse> findAll() {
        return clienteRepo.findAll()
                .stream()
                .map(this::convertirClienteADTO)
                .collect(Collectors.toList());
    }

    public clienteDTOResponse findByCodCliente(int codCliente) {
        Cliente cliente = clienteRepo.findByCodCliente(codCliente);
        if (cliente == null) {
            return null; // Si no existe, retorna null para que el Controlador devuelva 404
        }
        return convertirClienteADTO(cliente);
    }

    public List<clienteDTOResponse> findByNomCliente(String nomCliente) {
        return clienteRepo.findByNomCliente(nomCliente)
                .stream()
                .map(this::convertirClienteADTO)
                .collect(Collectors.toList());
    }

    public List<clienteDTOResponse> findByEstado(String indEstado) {
        return clienteRepo.findByIndEstado(indEstado)
                .stream()
                .map(this::convertirClienteADTO)
                .collect(Collectors.toList());
    }



    public clienteDTOResponse save(clienteDTORequest request) {

        // se extraen los datos  del DTO Request desde el front 
        String clienteNom = request.getNomCliente();
        String clienteTipIdentificacion = request.getTipIdentificacion();
        
        //  aqui tuve que onvertir los campos sensibles de String a byte para la Entidad 
        byte[] clienteNumIdentificacion = request.getNumIdentificacion() != null ? request.getNumIdentificacion().getBytes() : null;
        byte[] clienteNumTelefono = request.getNumTelefono() != null ? request.getNumTelefono().getBytes() : null;
        byte[] clienteDesCorreo = request.getDesCorreo() != null ? request.getDesCorreo().getBytes() : null;

        String clienteDesProvincia = request.getDesProvincia();
        String clienteDesCanton = request.getDesCanton();
        String clienteDesDistrito = request.getDesDistrito();
        String clienteDesBarrio = request.getDesBarrio();
        String clienteDesProfesion = request.getDesProfesion();
        String clienteDesActividadEconomica = request.getDesActividadEconomica();

        // Crear el objeto Entidad
        Cliente cliente = new Cliente();
        cliente.setNomCliente(clienteNom);
        cliente.setTipIdentificacion(clienteTipIdentificacion);
        cliente.setNumIdentificacion(clienteNumIdentificacion);
        cliente.setNumTelefono(clienteNumTelefono);
        cliente.setDesCorreo(clienteDesCorreo);
        cliente.setDesProvincia(clienteDesProvincia);
        cliente.setDesCanton(clienteDesCanton);
        cliente.setDesDistrito(clienteDesDistrito);
        cliente.setDesBarrio(clienteDesBarrio);
        cliente.setDesProfesion(clienteDesProfesion);
        cliente.setDesActividadEconomica(clienteDesActividadEconomica);
        cliente.setIndEstado("A"); // Estado activo por defecto

        // guarda en SQL Server y capturar la entidad guardada 
        Cliente clienteGuardado = clienteRepo.save(cliente);

        //Retornar el DTO Response usando el método de mapeo
        return convertirClienteADTO(clienteGuardado);
    }

    private clienteDTOResponse convertirClienteADTO(Cliente cliente) {
        // Convertir los campos byte[] de la entidad de vuelta a String para el DTO Response
        String numIdentificacionStr = cliente.getNumIdentificacion() != null ? new String(cliente.getNumIdentificacion()) : null;

        String numTelefonoStr = cliente.getNumTelefono() != null ? new String(cliente.getNumTelefono()) : null;
        
        String desCorreoStr = cliente.getDesCorreo() != null ? new String(cliente.getDesCorreo()) : null;

        return new clienteDTOResponse(
            cliente.getCodCliente(),
            cliente.getNomCliente(),
            cliente.getTipIdentificacion(),
            numIdentificacionStr,
            numTelefonoStr,
            desCorreoStr,
            cliente.getDesProvincia(),
            cliente.getDesCanton(),
            cliente.getDesDistrito(),
            cliente.getDesBarrio(),
            cliente.getDesProfesion(),
            cliente.getDesActividadEconomica(),
            cliente.getFecRegistro(),
            cliente.getIndEstado()
        );
    }
}