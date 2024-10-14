package SegundaEntrega.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import SegundaEntrega.api.DTO.ClienteDTO;
import SegundaEntrega.api.model.Cliente;
import SegundaEntrega.api.model.Panaderia;
import SegundaEntrega.api.repository.PanaderiaRepository;
import SegundaEntrega.api.services.ClienteService;
import utils.ApiResponse;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<?> getAllClients() {
        List<Cliente> clientes = clienteService.getAllClients();
        return ResponseEntity.ok().body(new ApiResponse("Clientes:", clientes));
    }


    @Autowired
    private PanaderiaRepository panaderiaRepository;

    @PostMapping("/createClient")
    public ResponseEntity<?> addClient(@RequestBody ClienteDTO clienteDTO) {
    Optional<Panaderia> panaderiaOpt = panaderiaRepository.findById(clienteDTO.getPanaderiaId());
    if (panaderiaOpt.isPresent()) {
        Cliente cliente = new Cliente();
        cliente.setNombre(clienteDTO.getNombre());
        cliente.setCorreo(clienteDTO.getCorreo());
        cliente.setTelefono(clienteDTO.getTelefono());
        cliente.setEdad(clienteDTO.getEdad());

        // Agregar la panadería al cliente
        cliente.addPanaderia(panaderiaOpt.get());

        // Guardar el cliente
        this.clienteService.saveClient(cliente);

        return ResponseEntity.ok().body(new ApiResponse("Cliente creado con éxito", cliente));
        } else {
        return ResponseEntity.badRequest().body(new ApiResponse("Error: Panadería no encontrada", null));
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteClient(@PathVariable Long id) {
    Optional<Cliente> clienteOpt = clienteService.getClientById(id);
    if (clienteOpt.isPresent()) {
        clienteService.deleteClient(id);
        return ResponseEntity.ok(new ApiResponse("Cliente eliminado con éxito", null));
        } else {
        return ResponseEntity.badRequest().body(new ApiResponse("Error: Cliente no encontrado", null));
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO) {
        Cliente updatedClient = clienteService.updateClient(id, clienteDTO);
        return ResponseEntity.ok(updatedClient);
    }
}
