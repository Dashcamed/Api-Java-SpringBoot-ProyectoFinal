package SegundaEntrega.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import SegundaEntrega.api.services.ClienteServiceRest;
import utils.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteServiceRest clienteService;

    @Autowired
    public ClienteController(ClienteServiceRest clienteService) {
        this.clienteService = clienteService;
    }

    // Importar cliente desde la API externa
    @PostMapping("/import/{id}")
    public ResponseEntity<ClienteDTO> importClienteFromApi(@PathVariable Long id) {
        try {
            ClienteDTO clienteDTO = clienteService.saveClientFromApi(id);
            return new ResponseEntity<>(clienteDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Obtener todos los clientes
    @GetMapping(path = "/all")
    public ResponseEntity<?> getAllClientes() {
        try {
            List<ClienteDTO> clientes = clienteService.getAllClients();
            return ResponseEntity.ok().body(new ApiResponse("Lista de Clientes", clientes));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse("NO HAY CLIENTES", e.getMessage()));
        }
    }

    // Crear un nuevo cliente a partir de un DTO
    @PostMapping("/createClient")
    public ResponseEntity<?> addClient(@RequestBody ClienteDTO clienteDTO) {
        try {
            ClienteDTO savedCliente = clienteService.saveClientFromDTO(clienteDTO);
            return ResponseEntity.ok().body(new ApiResponse("Cliente creado con éxito", savedCliente));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse("Error: " + e.getMessage(), null));
        }
    }

    // Eliminar cliente por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable Long id) {
        try {
            clienteService.deleteClient(id);
            return ResponseEntity.ok().body(new ApiResponse("Cliente Eliminado", id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse("Error: No se pudo eliminar el cliente", e.getMessage()));
        }
    }

    // Actualizar cliente existente por ID
    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> updateCliente(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO) {
        try {
            ClienteDTO updatedClient = clienteService.updateClient(id, clienteDTO);
            return ResponseEntity.ok(updatedClient);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
