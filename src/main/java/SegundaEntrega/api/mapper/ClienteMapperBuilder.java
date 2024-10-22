package SegundaEntrega.api.mapper;

import org.springframework.stereotype.Component;

import SegundaEntrega.api.DTO.ClienteDTO;
import SegundaEntrega.api.DTO.PanaderiaDTO;
import SegundaEntrega.api.model.Cliente;
import SegundaEntrega.api.model.Panaderia;

@Component
public class ClienteMapperBuilder {

    public ClienteDTO toDTO(Cliente cliente) {
        if (cliente == null) {
            return null;
        }

        return ClienteDTO.builder()
                .id(cliente.getId())
                .name(cliente.getName())
                .email(cliente.getEmail())
                .phone(cliente.getPhone())
                .build();
    }

    public Cliente toEntity(ClienteDTO dto) {
        if (dto == null) {
            return null;
        }

        return Cliente.builder()
                .id(dto.getId())
                .name(dto.getName())
                .email(dto.getEmail())
                .phone(dto.getPhone())
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
