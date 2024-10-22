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
                .name(cliente.getName())
                .email(cliente.getEmail())
                .phone(cliente.getPhone())
                .build();
    }

    public Cliente toEntity(ClienteDTO clienteDTO) {
        if (clienteDTO == null) {
            throw new IllegalArgumentException("El clienteDTO no puede ser nulo");
        }

        Cliente cliente = new Cliente();
        cliente.setName(clienteDTO.getName());
        cliente.setEmail(clienteDTO.getEmail());
        cliente.setPhone(clienteDTO.getPhone());
        return cliente;
    }
}
