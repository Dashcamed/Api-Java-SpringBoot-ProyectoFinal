package SegundaEntrega.api.mapper;

import org.springframework.stereotype.Component;

import SegundaEntrega.api.DTO.PanaderiaCreateDTO;
import SegundaEntrega.api.DTO.PanaderiaDTO;
import SegundaEntrega.api.model.Panaderia;

import java.util.stream.Collectors;

@Component
public class PanaderiaMapper {

    private final ClienteMapper clienteMapper;
    private final ProductoMapper productoMapper;

    public PanaderiaMapper(ClienteMapper clienteMapper, ProductoMapper productoMapper) {
        this.clienteMapper = clienteMapper;
        this.productoMapper = productoMapper;
    }

    public PanaderiaDTO toDTOPanaderia(Panaderia panaderia, boolean includeRelations) {
        if (panaderia == null) {
            throw new IllegalArgumentException("La entidad no puede ser nula");
        }

        PanaderiaDTO.PanaderiaDTOBuilder builder = PanaderiaDTO.builder()
                .id(panaderia.getId())
                .nombre(panaderia.getNombre())
                .direccion(panaderia.getDireccion())
                .telefono(panaderia.getTelefono());

        if (includeRelations) {
            builder.clientes(panaderia.getClientes().stream()
                    .map(clienteMapper::toDTOCliente)
                    .collect(Collectors.toSet()));
            builder.productos(panaderia.getProductos().stream()
                    .map(productoMapper::toDTOProducto)
                    .collect(Collectors.toSet()));
        }

        return builder.build();
    }

    public Panaderia toEntity(PanaderiaCreateDTO panaderiaCreateDTO) {
    if (panaderiaCreateDTO == null) {
        throw new IllegalArgumentException("La panaderiaCreateDTO no puede ser nula");
    }

    Panaderia panaderia = new Panaderia();
    panaderia.setId(panaderiaCreateDTO.getId());
    panaderia.setNombre(panaderiaCreateDTO.getNombre());
    panaderia.setDireccion(panaderiaCreateDTO.getDireccion());
    panaderia.setTelefono(panaderiaCreateDTO.getTelefono());
    // No asignar relaciones en la creación o actualización.
    return panaderia;
}
}
