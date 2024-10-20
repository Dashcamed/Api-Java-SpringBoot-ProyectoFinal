package SegundaEntrega.api.services;

import java.util.List;

import SegundaEntrega.api.DTO.ClienteDTO;

public interface ClienteService {
    ClienteDTO getClientById(Long id);

    List<ClienteDTO> getAllClients();

    ClienteDTO saveClienteDTO(ClienteDTO clienteDTO);

    void saveClientFromApi(ClienteDTO clienteDTO);

    void deleteClient(Long id);


    ClienteDTO upClienteDTO(Long id, ClienteDTO clienteDTO);
}
