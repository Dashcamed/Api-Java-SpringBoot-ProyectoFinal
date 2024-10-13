package SegundaEntrega.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SegundaEntrega.api.DTO.ClienteDTO;
import SegundaEntrega.api.model.Cliente;
import SegundaEntrega.api.model.Panaderia;
import SegundaEntrega.api.repository.ClienteRepository;
import SegundaEntrega.api.repository.PanaderiaRepository;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> getAllClients() {
        return clienteRepository.findAll(); // Cambiado a findAll para obtener todos los clientes
    }

    // buscar cliente por id
    public Optional<Cliente> getClientById(Long id) {
        return clienteRepository.findById(id);
    }

    // guardar clientes
    public Cliente saveClient(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    //borrar cliente con validacion
    public void deleteClient(Long id) {
        if(clienteRepository.existsById(id)){
            clienteRepository.deleteById(id);
        } else {
            System.out.println("el cliente no existe");
        }
    }

    @Autowired
    private PanaderiaRepository panaderiaRepository;

    public Cliente updateClient(Long id, ClienteDTO clienteDTO) {
        Optional<Cliente> optionalClient = clienteRepository.findById(id);

        if (optionalClient.isPresent()) {
            Cliente existingClient = optionalClient.get();

            // Actualizar los campos con los datos del DTO
            existingClient.setNombre(clienteDTO.getNombre());
            existingClient.setCorreo(clienteDTO.getCorreo());
            existingClient.setTelefono(clienteDTO.getTelefono());
            existingClient.setEdad(clienteDTO.getEdad());
            existingClient.setFechaDeModificacion(FechaService.getFechaActual());

            // Buscar la panadería por ID si es necesario actualizar
            if (clienteDTO.getPanaderiaId() != null) {
                Optional<Panaderia> optionalPanaderia = panaderiaRepository.findById(clienteDTO.getPanaderiaId());
                if (optionalPanaderia.isPresent()) {
                    existingClient.setPanaderias(optionalPanaderia.get());
                } else {
                    throw new RuntimeException("Panadería no encontrada con ID: " + clienteDTO.getPanaderiaId());
                }
            }

            // Guardar el cliente actualizado
            return clienteRepository.save(existingClient);
        } else {
            throw new RuntimeException("Cliente no encontrado con ID: " + id);
        }
    }
}
