package com.facturacion.servicio;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.facturacion.dto.FacturaDTO;
import com.facturacion.dto.FacturaDetalleDTO;
import com.facturacion.entidades.*;
import com.facturacion.repositorio.*;

@Service
@Transactional
public class FacturaServicio {

    private final FacturaRepo facturaRepo;
    private final ProductoServicioRepo productoRepo;

    public FacturaServicio(FacturaRepo facturaRepo, ProductoServicioRepo productoRepo) {
        this.facturaRepo = facturaRepo;
        this.productoRepo = productoRepo;
    }

    @Transactional(readOnly = true)
    public List<FacturaDTO> findAll() {
        return facturaRepo.findAll().stream().map(this::convertirADTO).collect(Collectors.toList());
    }

    // es transactional y readonly porque no se va a modificar la base de datos, solo se va a leer
    @Transactional(readOnly = true)
    public FacturaDTO findById(int numFactura) {
        Factura f = facturaRepo.findByNumFactura(numFactura);
        return f != null ? convertirADTO(f) : null;
    }

    // Inserción usando el Stored Procedure transaccional (REQ 002)
    public FacturaDTO crearFactura(FacturaDTO dto) {
        Integer numFacturaGenerado = facturaRepo.spInsertarFactura(
            dto.getCodCliente(), dto.getCondicionVenta(), dto.getMedioPago(),
            dto.getMoneda() != null ? dto.getMoneda() : "CRC",
            dto.getTipoCambio(), dto.getObservaciones(),
            dto.getSubtotal(), dto.getTotalImpuestos(), dto.getMontoTotal()
        );

        Factura factura = facturaRepo.findByNumFactura(numFacturaGenerado);

        // Guardar detalles
        if (dto.getDetalles() != null && !dto.getDetalles().isEmpty()) {
            for (FacturaDetalleDTO detDto : dto.getDetalles()) {
                ProductoServicio prod = productoRepo.findByCodProducto(detDto.getCodProducto());
                FacturaDetalle det = new FacturaDetalle();
                det.setFactura(factura);
                det.setProducto(prod);
                det.setCantidad(detDto.getCantidad());
                det.setMontoLinea(detDto.getMontoLinea());
                factura.getDetalles().add(det);
            }
            facturaRepo.save(factura);
        }

        return convertirADTO(factura);
    }

    // en todas tiene que estar porque como tal se necesita convetir la entidad

    private FacturaDTO convertirADTO(Factura f) {
        FacturaDTO dto = new FacturaDTO();
        dto.setNumFactura(f.getNumFactura());
        dto.setCodCliente(f.getCliente() != null ? f.getCliente().getCodCliente() : null);
        dto.setNomCliente(f.getCliente() != null ? f.getCliente().getNomCliente() : null);
        dto.setFecFactura(f.getFecFactura());
        dto.setCondicionVenta(f.getCondicionVenta());
        dto.setMedioPago(f.getMedioPago());
        dto.setMoneda(f.getMoneda());
        dto.setTipoCambio(f.getTipoCambio());
        dto.setObservaciones(f.getObservaciones());
        dto.setSubtotal(f.getSubtotal());
        dto.setTotalImpuestos(f.getTotalImpuestos());
        dto.setMontoTotal(f.getMontoTotal());

        if (f.getDetalles() != null) {
            dto.setDetalles(f.getDetalles().stream().map(d -> new FacturaDetalleDTO(
                d.getProducto().getCodProducto(),
                d.getProducto().getDesProducto(),
                d.getCantidad(),
                d.getMontoLinea()
            )).collect(Collectors.toList()));
        }
        return dto;
    }
}