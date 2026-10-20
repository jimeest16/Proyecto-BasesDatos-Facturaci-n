package com.facturacion.servicio;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.facturacion.dto.ProductoServicioDTO;
import com.facturacion.entidades.ProductoServicio;
import com.facturacion.entidades.TiposImpuesto;
import com.facturacion.repositorio.ProductoServicioRepo;
import com.facturacion.repositorio.TiposImpuestoRepo;


@Service
@Transactional
public class ProductoServicioServicio {

    private final ProductoServicioRepo repo;
    private final TiposImpuestoRepo tipoImpuestoRepo;

    public ProductoServicioServicio(ProductoServicioRepo repo, TiposImpuestoRepo tipoImpuestoRepo) {
        this.repo = repo;
        this.tipoImpuestoRepo = tipoImpuestoRepo;
    }

    @Transactional(readOnly = true)
    public List<ProductoServicioDTO> findAll() {
        return repo.findAll().stream().map(this::convertirADTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ProductoServicioDTO findById(int id) {
        ProductoServicio entity = repo.findByCodProducto(id);
        return entity != null ? convertirADTO(entity) : null;
    }

    public ProductoServicioDTO save(ProductoServicioDTO dto) {
        TiposImpuesto tipoImp = tipoImpuestoRepo.findByCodTipoImpuesto(dto.getCodTipoImpuesto());
        
        ProductoServicio entity = new ProductoServicio();
        entity.setCodCabys(dto.getCodCabys());
        entity.setDesProducto(dto.getDesProducto());
        entity.setUnidadMedida(dto.getUnidadMedida());
        entity.setPrecio(dto.getPrecio());
        entity.setTipoImpuesto(tipoImp);
        entity.setIndEstado("A");

        return convertirADTO(repo.save(entity));
    }

    private ProductoServicioDTO convertirADTO(ProductoServicio entity) {
        return new ProductoServicioDTO(
            entity.getCodProducto(), entity.getCodCabys(), entity.getDesProducto(),
            entity.getUnidadMedida(), entity.getPrecio(),
            entity.getTipoImpuesto() != null ? entity.getTipoImpuesto().getCodTipoImpuesto() : null,
            entity.getIndEstado()
        );
    }
}