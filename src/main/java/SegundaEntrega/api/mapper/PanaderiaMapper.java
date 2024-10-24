package SegundaEntrega.api.mapper;

import org.springframework.stereotype.Component;
import SegundaEntrega.api.DTO.PanaderiaDTO;
import SegundaEntrega.api.model.Panaderia;

@Component
public class PanaderiaMapper {

    public PanaderiaDTO toDTOPanaderia(Panaderia panaderia) {
        if (panaderia == null) {
            throw new IllegalArgumentException("La entidad no puede ser nula");
        }

        return PanaderiaDTO.builder()
                .id(panaderia.getId())
                .nombre(panaderia.getNombre())
                .direccion(panaderia.getDireccion())
                .telefono(panaderia.getTelefono())
                .build();
    }

    public Panaderia toEntity(PanaderiaDTO panaderiaDTO) {
        if (panaderiaDTO == null) {
            throw new IllegalArgumentException("La panaderiaDTO no puede ser nula");
        }

        Panaderia panaderia = new Panaderia();
        panaderia.setId(panaderiaDTO.getId());
        panaderia.setNombre(panaderiaDTO.getNombre());
        panaderia.setDireccion(panaderiaDTO.getDireccion());
        panaderia.setTelefono(panaderiaDTO.getTelefono());
        return panaderia;
    }
}
