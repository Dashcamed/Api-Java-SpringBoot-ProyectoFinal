package SegundaEntrega.api.mapper;

import org.springframework.stereotype.Component;

import SegundaEntrega.api.DTO.ProductoDTO;
import SegundaEntrega.api.model.Producto;

@Component
public class ProductoMapper {

    public ProductoDTO toDTO(Producto producto){
        if(producto == null){
            throw new IllegalArgumentException("la entidad no puede ser nula");
        }
        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setNombre(productoDTO.getNombre());
        productoDTO.setPrecio(productoDTO.getPrecio());
        productoDTO.setStock(productoDTO.getStock());
        productoDTO.setCategoria(productoDTO.getCategoria());
        productoDTO.setPanaderiaId(productoDTO.getPanaderiaId());

        return productoDTO;

    }

    public Producto toEntity(ProductoDTO productoDTO) {
        if (productoDTO == null) {
            throw new IllegalArgumentException("El productoDTO no puede ser nulo");
        }

        Producto producto = new Producto();
        producto.setNombre(productoDTO.getNombre());
        producto.setPrecio(productoDTO.getPrecio());
        producto.setStock(productoDTO.getStock());
        producto.setCategoria(productoDTO.getCategoria());
        producto.setPanaderiaId(productoDTO.getPanaderiaId());
        return producto;
    }
}
