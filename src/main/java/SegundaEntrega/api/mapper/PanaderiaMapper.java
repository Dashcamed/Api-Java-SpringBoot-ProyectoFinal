package SegundaEntrega.api.mapper;

import org.springframework.stereotype.Component;

import SegundaEntrega.api.DTO.PanaderiaDTO;
import SegundaEntrega.api.model.Panaderia;

@Component
public class PanaderiaMapper {

    public PanaderiaDTO toDTO(Panaderia panaderia){
        if (panaderia == null){
            throw new IllegalArgumentException("la entidad no puede ser nula");

        }
        PanaderiaDTO panaderiaDTO = new PanaderiaDTO();
        panaderiaDTO.setNombre(panaderia.getNombre());
        panaderiaDTO.setDireccion(panaderia.getDireccion());
        panaderiaDTO.setTelefono(panaderia.getTelefono());
        return panaderiaDTO;
    }

    public Panaderia toEntity(PanaderiaDTO panaderiaDTO) {
        if (panaderiaDTO == null) {
            throw new IllegalArgumentException("La panaderiaDTO no puede ser nulo");
        }

        Panaderia panaderia = new Panaderia();
        panaderia.setNombre(panaderiaDTO.getNombre());
        panaderia.setDireccion(panaderiaDTO.getDireccion());
        panaderia.setTelefono(panaderiaDTO.getTelefono());
        return panaderia;
    }
}
