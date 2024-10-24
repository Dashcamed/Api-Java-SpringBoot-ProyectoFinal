package SegundaEntrega.api.mapper;
import java.util.Set;
import org.springframework.stereotype.Component;
import SegundaEntrega.api.DTO.ProductoDTO;
import SegundaEntrega.api.model.Panaderia;
import SegundaEntrega.api.model.Producto;
import java.util.stream.Collectors;

@Component
public class ProductoMapper {

    public ProductoDTO toDTOProducto(Producto producto) {
        if (producto == null) {
            throw new IllegalArgumentException("La entidad no puede ser nula");
        }

        Set<Long> panaderiaIds = producto.getPanaderias().stream()
                .map(Panaderia::getId)  // Obtener solo los IDs de las panaderías
                .collect(Collectors.toSet());

        return ProductoDTO.builder()
                .nombre(producto.getNombre())
                .precio(producto.getPrecio())
                .stock(producto.getStock())
                .categoria(producto.getCategoria())
                .panaderiaIds(panaderiaIds)
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
