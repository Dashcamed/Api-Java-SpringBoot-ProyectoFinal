package SegundaEntrega.api.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SegundaEntrega.api.DTO.ClienteDTO;
import SegundaEntrega.api.mapper.ClienteMapper;
import SegundaEntrega.api.model.Cliente;
import SegundaEntrega.api.model.ClientePanaderia;
import SegundaEntrega.api.model.Panaderia;
import SegundaEntrega.api.repository.ClienteRepository;
import SegundaEntrega.api.repository.PanaderiaRepository;

@Service
public class ClienteService {

    @Autowired
    private final ClienteRepository clienteRepository;
    @Autowired
    private final PanaderiaRepository panaderiaRepository;
    @Autowired
    private final ClienteMapper clienteMapper;

    public ClienteService(ClienteRepository clienteRepository, PanaderiaRepository panaderiaRepository, ClienteMapper clienteMapper) {
        this.clienteRepository = clienteRepository;
        this.panaderiaRepository = panaderiaRepository;
        this.clienteMapper = clienteMapper;
    }

    public List<ClienteDTO> getAllClients() {
        return clienteRepository.findAll().stream()
                .map(clienteMapper::toDTOCliente)
                .collect(Collectors.toList());
    }

    public Optional<ClienteDTO> getClientById(Long id) {
        return clienteRepository.findById(id).map(clienteMapper::toDTOCliente);
    }

    public ClienteDTO saveClient(ClienteDTO clienteDTO) {
        Cliente cliente = clienteMapper.toEntity(clienteDTO);
        Cliente savedCliente = clienteRepository.save(cliente);
        return clienteMapper.toDTOCliente(savedCliente);
    }

    public void deleteClient(Long id) {
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
        } else {
            System.out.println("El cliente no existe");
        }
    }

    public ClienteDTO updateClient(Long id, ClienteDTO clienteDTO) {
        Optional<Cliente> optionalClient = clienteRepository.findById(id);

        if (optionalClient.isPresent()) {
            Cliente existingClient = optionalClient.get();

            existingClient.setNombre(clienteDTO.getNombre());
            existingClient.setCorreo(clienteDTO.getCorreo());
            existingClient.setTelefono(clienteDTO.getTelefono());
            existingClient.setEdad(clienteDTO.getEdad());
            existingClient.setFechaDeModificacion(FechaService.getFechaActual());
            existingClient.getClientePanaderias().clear();

            if (clienteDTO.getPanaderiaId() != null) {
                Optional<Panaderia> optionalPanaderia = panaderiaRepository.findById(clienteDTO.getPanaderiaId());
                if (optionalPanaderia.isPresent()) {
                    ClientePanaderia clientePanaderia = new ClientePanaderia(existingClient, optionalPanaderia.get());
                    existingClient.getClientePanaderias().add(clientePanaderia);
                } else {
                    throw new RuntimeException("Panadería no encontrada con ID: " + clienteDTO.getPanaderiaId());
                }
            }

            Cliente updatedClient = clienteRepository.save(existingClient);
            return clienteMapper.toDTOCliente(updatedClient);

        } else {
            throw new RuntimeException("Cliente no encontrado con ID: " + id);
        }
    }
}
