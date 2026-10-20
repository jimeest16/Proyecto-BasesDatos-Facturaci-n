package com.facturacion.servicio;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.facturacion.dto.NotaCreditoDebitoDTO;
import com.facturacion.entidades.Factura;
import com.facturacion.entidades.NotaCreditoDebito;
import com.facturacion.repositorio.FacturaRepo;
import com.facturacion.repositorio.NotaCreditoDebitoRepo;

@Service
@Transactional
public class NotaCreditoDebitoServicio {

    private final NotaCreditoDebitoRepo repo;
    private final FacturaRepo facturaRepo;

    public NotaCreditoDebitoServicio(NotaCreditoDebitoRepo repo, FacturaRepo facturaRepo) {
        this.repo = repo;
        this.facturaRepo = facturaRepo;
    }

    @Transactional(readOnly = true)
    public List<NotaCreditoDebitoDTO> findAll() {
        return repo.findAll().stream().map(this::convertirADTO).collect(Collectors.toList());
    }

    public NotaCreditoDebitoDTO save(NotaCreditoDebitoDTO dto) {
        Factura f = facturaRepo.findByNumFactura(dto.getNumFactura());
        NotaCreditoDebito nota = new NotaCreditoDebito();
        nota.setFactura(f);
        nota.setIndTipo(dto.getIndTipo());
        nota.setDesMotivo(dto.getDesMotivo());

        return convertirADTO(repo.save(nota));
    }

    private NotaCreditoDebitoDTO convertirADTO(NotaCreditoDebito n) {
        return new NotaCreditoDebitoDTO(
            n.getCodNota(), n.getFecNota(),
            n.getFactura() != null ? n.getFactura().getNumFactura() : null,
            n.getIndTipo(), n.getDesMotivo()
        );
    }
}