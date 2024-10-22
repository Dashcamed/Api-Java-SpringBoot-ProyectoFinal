package SegundaEntrega.api.mapper;

import org.springframework.stereotype.Component;

import SegundaEntrega.api.DTO.PanaderiaDTO;
import SegundaEntrega.api.DTO.ProductoDTO;
import SegundaEntrega.api.model.Panaderia;
import SegundaEntrega.api.model.Producto;

@Component
public class ProductoMapperBuilder {

    public ProductoDTO toDTO(Producto producto) {
        if (producto == null) {
            return null;
        }

        return ProductoDTO.builder()
                .nombre(producto.getNombre())
                .precio(producto.getPrecio())
                .stock(producto.getStock())
                .build();
    }

    public Producto toEntity(ProductoDTO dto) {
        if (dto == null) {
            return null;
        }

        return Producto.builder()
                .nombre(dto.getNombre())
                .precio(dto.getPrecio())
                .stock(dto.getStock())
                .build();
    }

    public static PanaderiaDTO toPanaderiaDTO(Panaderia panaderia) {
        PanaderiaDTO dto = new PanaderiaDTO();
        dto.setNombre(panaderia.getNombre());
        dto.setDireccion(panaderia.getDireccion());
        dto.setTelefono(panaderia.getTelefono());
        return dto;
    }
}
