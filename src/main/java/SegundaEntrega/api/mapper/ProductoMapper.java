package SegundaEntrega.api.mapper;
import org.springframework.stereotype.Component;
import SegundaEntrega.api.DTO.ProductoDTO;
import SegundaEntrega.api.model.Producto;

@Component
public class ProductoMapper {

    public ProductoDTO toDTOProducto(Producto producto) {
        if (producto == null) {
            throw new IllegalArgumentException("La entidad no puede ser nula");
        }

        return ProductoDTO.builder()
                .nombre(producto.getNombre())
                .precio(producto.getPrecio())
                .stock(producto.getStock())
                .categoria(producto.getCategoria())
                .build();
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
        return producto;
    }
}
