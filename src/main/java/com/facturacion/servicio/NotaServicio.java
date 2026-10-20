package com.facturacion.servicio;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.facturacion.dto.NotaCreditoDebitoDTO;
import com.facturacion.entidades.NotaCreditoDebito;
import com.facturacion.repositorio.FacturaRepo;
import com.facturacion.repositorio.NotaCreditoDebitoRepo;

@Service
@Transactional
public class NotaServicio {

    private final NotaCreditoDebitoRepo repo;
    private final FacturaRepo facturaRepo;

    public NotaServicio(NotaCreditoDebitoRepo repo, FacturaRepo facturaRepo) {
        this.repo = repo;
        this.facturaRepo = facturaRepo;
    }

    @Transactional(readOnly = true)
    public List<NotaCreditoDebitoDTO> findAll() {
        return repo.findAll()
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

  
    public NotaCreditoDebitoDTO save(NotaCreditoDebitoDTO dto) {
        // Invoca el procedimiento almacenado mapeado en el repositorio
        Integer codNotaGenerado = repo.spInsertarNota(
            dto.getNumFactura(),
            dto.getIndTipo(),
            dto.getDesMotivo()
        );

      
        if (codNotaGenerado != null && codNotaGenerado > 0) {
            NotaCreditoDebito notaGuardada = repo.findById(codNotaGenerado).orElse(null);
            if (notaGuardada != null) {
                return convertirADTO(notaGuardada);
            }
        }

       
        return dto;
    }

    private NotaCreditoDebitoDTO convertirADTO(NotaCreditoDebito n) {
        return new NotaCreditoDebitoDTO(
            n.getCodNota(), 
            n.getFecNota(),
            n.getFactura() != null ? n.getFactura().getNumFactura() : null,
            n.getIndTipo(), 
            n.getDesMotivo()
        );
    }
}