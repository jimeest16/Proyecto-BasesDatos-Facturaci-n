package com.facturacion.servicio;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.facturacion.dto.TiposImpuestoDTO;
import com.facturacion.entidades.TiposImpuesto;
import com.facturacion.repositorio.TiposImpuestoRepo;

@Service
@Transactional
public class TiposImpuestoServicio {

    private final TiposImpuestoRepo repo;

    public TiposImpuestoServicio(TiposImpuestoRepo repo) {
        this.repo = repo;
    }

    @Transactional(readOnly = true)
    public List<TiposImpuestoDTO> findAll() {
        return repo.findAll().stream().map(this::convertirADTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public TiposImpuestoDTO findById(String code) {
        TiposImpuesto entity = repo.findByCodTipoImpuesto(code);
        return entity != null ? convertirADTO(entity) : null;
    }

    public TiposImpuestoDTO save(TiposImpuestoDTO dto) {
        TiposImpuesto entity = new TiposImpuesto(
            dto.getCodTipoImpuesto(), dto.getDesTipoImpuesto(),
            dto.getPorcentaje(), dto.getTarifa(), dto.getIndEstado() != null ? dto.getIndEstado() : "A"
        );
        return convertirADTO(repo.save(entity));
    }

    private TiposImpuestoDTO convertirADTO(TiposImpuesto entity) {
        return new TiposImpuestoDTO(
            entity.getCodTipoImpuesto(), entity.getDesTipoImpuesto(),
            entity.getPorcentaje(), entity.getTarifa(), entity.getIndEstado()
        );
    }
}