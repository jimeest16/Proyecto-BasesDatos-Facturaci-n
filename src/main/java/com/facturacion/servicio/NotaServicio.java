package com.facturacion.servicio;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.facturacion.dto.NotaCreditoDebitoDTO;
import com.facturacion.entidades.NotaCreditoDebito;
import com.facturacion.repositorio.NotaCreditoDebitoRepo;

@Service
@Transactional
public class NotaServicio {

    private final NotaCreditoDebitoRepo repo;

    public NotaServicio(NotaCreditoDebitoRepo repo) {
        this.repo = repo;
    }

    @Transactional(readOnly = true)
    public List<NotaCreditoDebitoDTO> findAll() {
        return repo.findAll().stream().map(this::convertirADTO).collect(Collectors.toList());
    }

    public NotaCreditoDebitoDTO save(NotaCreditoDebitoDTO dto) {
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

        dto.setCodNota(codNotaGenerado);
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