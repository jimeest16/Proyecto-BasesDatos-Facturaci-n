package com.facturacion.servicio;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.facturacion.dto.ClienteDTO;
import com.facturacion.entidades.Cliente;
import com.facturacion.repositorio.ClienteRepo;

@Service
@Transactional
public class ClienteServicio {

    private final ClienteRepo clienteRepo;

    public ClienteServicio(ClienteRepo clienteRepo) {
        this.clienteRepo = clienteRepo;
    }

    @Transactional(readOnly = true)
    public List<ClienteDTO> findAll() {
        return clienteRepo.findAll()
                .stream()
                .map(this::convertirClienteADTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
  public ClienteDTO findByCodCliente(int codCliente) {
    Cliente cliente = clienteRepo.findByCodCliente(codCliente);
    if (cliente == null) {
        return null; 
    }
    return convertirClienteADTO(cliente);
}

    @Transactional(readOnly = true)
    public List<ClienteDTO> findByNomCliente(String nomCliente) {
        return clienteRepo.findByNomClienteContainingIgnoreCase(nomCliente)
                .stream()
                .map(this::convertirClienteADTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ClienteDTO> findByEstado(String indEstado) {
        return clienteRepo.findByIndEstado(indEstado)
                .stream()
                .map(this::convertirClienteADTO)
                .collect(Collectors.toList());
    }

 
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
            request.getDesBarrio(),
            request.getDesProfesion(),
            request.getDesActividadEconomica()
        );

        // Recuperar y devolver el cliente recién guardado
        return findByCodCliente(idGenerado);
    }

   
    public ClienteDTO actualizar(int id, ClienteDTO request) {
        List<Cliente> optCliente = (List<Cliente>) clienteRepo.findByCodCliente(id);
        if (optCliente.isEmpty()) {
            return null;
        }

        Cliente clienteExistente = optCliente.get(0);
        clienteExistente.setNomCliente(request.getNomCliente());
        clienteExistente.setTipIdentificacion(request.getTipIdentificacion());

        
        if (request.getNumIdentificacion() != null) {
            clienteExistente.setNumIdentificacion(request.getNumIdentificacion().getBytes());
        }
        if (request.getNumTelefono() != null) {
            clienteExistente.setNumTelefono(request.getNumTelefono().getBytes());
        }
        if (request.getDesCorreo() != null) {
            clienteExistente.setDesCorreo(request.getDesCorreo().getBytes());
        }

        clienteExistente.setDesProvincia(request.getDesProvincia());
        clienteExistente.setDesCanton(request.getDesCanton());
        clienteExistente.setDesDistrito(request.getDesDistrito());
        clienteExistente.setDesBarrio(request.getDesBarrio());
        clienteExistente.setDesProfesion(request.getDesProfesion());
        clienteExistente.setDesActividadEconomica(request.getDesActividadEconomica());
        
        if (request.getIndEstado() != null) {
            clienteExistente.setIndEstado(request.getIndEstado());
        }

        Cliente clienteActualizado = clienteRepo.save(clienteExistente);
        return convertirClienteADTO(clienteActualizado);
    }

    // ELIMINACIÓN
    public boolean eliminar(int id) {
        List<Cliente> optCliente = (List<Cliente>) clienteRepo.findByCodCliente(id);
        if (optCliente.isEmpty()) {
            return false;
        }
        clienteRepo.delete(optCliente.get(0));
        return true;
    }

    // metodo que convierte un cliente con los atributos que solo vamos a necesitar en el front
    private ClienteDTO convertirClienteADTO(Cliente cliente) {
        String numIdentificacionStr = cliente.getNumIdentificacion() != null ? new String(cliente.getNumIdentificacion()) : null;
        String numTelefonoStr = cliente.getNumTelefono() != null ? new String(cliente.getNumTelefono()) : null;
        String desCorreoStr = cliente.getDesCorreo() != null ? new String(cliente.getDesCorreo()) : null;

        return new ClienteDTO(
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