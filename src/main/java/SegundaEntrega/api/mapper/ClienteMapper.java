package SegundaEntrega.api.mapper;

import org.springframework.stereotype.Component;
import SegundaEntrega.api.DTO.ClienteDTO;
import SegundaEntrega.api.model.Cliente;
import SegundaEntrega.api.model.Panaderia;
import java.util.stream.Collectors;

import java.util.Set;


@Component
public class ClienteMapper {

    public ClienteDTO toDTOCliente(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("La entidad no puede ser nula");
        }

        Set<Long> panaderiaIds = cliente.getPanaderias().stream()
                .map(Panaderia::getId)
                .collect(Collectors.toSet());

        return ClienteDTO.builder()
                .id(cliente.getId())
                .name(cliente.getName())
                .email(cliente.getEmail())
                .phone(cliente.getPhone())
                .panaderiaIds(panaderiaIds)
                .build();
    }

    public Cliente toEntity(ClienteDTO clienteDTO) {
        if (clienteDTO == null) {
            throw new IllegalArgumentException("El clienteDTO no puede ser nulo");
        }

        Cliente cliente = new Cliente();
        cliente.setId(clienteDTO.getId());
        cliente.setName(clienteDTO.getName());
        cliente.setEmail(clienteDTO.getEmail());
        cliente.setPhone(clienteDTO.getPhone());
        return cliente;
    }
}
