package SegundaEntrega.api.mapper;

import org.springframework.stereotype.Component;

import SegundaEntrega.api.DTO.ClienteDTO;
import SegundaEntrega.api.model.Cliente;

@Component
public class ClienteMapper {

    public ClienteDTO toDTOCliente(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("La entidad no puede ser nula");
        }

        return ClienteDTO.builder()
                .nombre(cliente.getNombre())
                .correo(cliente.getCorreo())
                .telefono(cliente.getTelefono())
                .edad(cliente.getEdad())
                .panaderiaId(cliente.getPanaderia() != null ? ((Cliente) cliente.getPanaderia()).getId() : null)
                .clientePanaderias(cliente.getClientePanaderias())
                .build();
    }

    public Cliente toEntity(ClienteDTO clienteDTO) {
        if (clienteDTO == null) {
            throw new IllegalArgumentException("El clienteDTO no puede ser nulo");
        }

        Cliente cliente = new Cliente();
        cliente.setNombre(clienteDTO.getNombre());
        cliente.setCorreo(clienteDTO.getCorreo());
        cliente.setTelefono(clienteDTO.getTelefono());
        cliente.setEdad(clienteDTO.getEdad());
        cliente.setClientePanaderias(clienteDTO.getClientePanaderias());
        return cliente;
    }
}
