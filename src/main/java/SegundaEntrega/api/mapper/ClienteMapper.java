package SegundaEntrega.api.mapper;

import org.springframework.stereotype.Component;

import SegundaEntrega.api.DTO.ClienteDTO;
import SegundaEntrega.api.model.Cliente;

@Component
public class ClienteMapper {
    public ClienteDTO toDTO(Cliente cliente){
        if (cliente == null){
            throw new IllegalArgumentException("la entidad no puede ser nula");

        }
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setNombre(cliente.getNombre());
        clienteDTO.setCorreo(cliente.getCorreo());
        clienteDTO.setTelefono(cliente.getTelefono());
        clienteDTO.setEdad(cliente.getEdad());
        clienteDTO.setClientePanaderias(cliente.getClientePanaderias());
        return clienteDTO;
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
