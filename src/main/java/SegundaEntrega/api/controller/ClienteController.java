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
import SegundaEntrega.api.mapper.ClienteMapper;
import SegundaEntrega.api.services.ClienteServiceRest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import utils.ApiResponseMsg;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteServiceRest clienteService;

    @Autowired
    public ClienteController(ClienteServiceRest clienteService) {
        this.clienteService = clienteService;
    }
    @Autowired
    public ClienteMapper clienteMapper;
    // Importar cliente desde la API externa
    @PostMapping("/import/{id}")
    @Operation(summary = "Importa los clientes al modelo con datos de una api externa", description = "Inyecta en el modelo los datos de la api y los transforma a la entidad que se asocia.")
    public ResponseEntity<ClienteDTO> importClienteFromApi(@PathVariable Long id) {
        try {
            ClienteDTO clienteDTO = clienteService.saveClientFromApi(id);
            return new ResponseEntity<>(clienteDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Crear un nuevo cliente a partir de un DTO
    @PostMapping("/createClient")
    @Operation(summary = "Crear un cliente asociado a una panaderia", description = "Retorna un estado de http si se crea.")
    public ResponseEntity<?> addClient(@RequestBody ClienteDTO clienteDTO) {
        ClienteDTO createdProduct = clienteService.saveClienteDTO(clienteDTO);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    // Obtener todos los clientes
    @GetMapping(path = "/all")
    @Operation(summary = "Obtener todas los clientes", description = "Retorna los clientes creados y los de la api")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = ClienteDTO.class))),
            @ApiResponse(responseCode = "404", description = "Clients not found", content = @Content(schema = @Schema(implementation = ApiResponse.class), examples = {
                    @ExampleObject(name = "ClientsNotFound", value = "{\"message\": \"Client not found\"}", description = "Clientes no encontrados")
            }))
    })
    public ResponseEntity<?> getAllClientes() {
        try {
            List<ClienteDTO> clientes = clienteService.getAllClients();
            return ResponseEntity.ok().body(new ApiResponseMsg("Lista de Clientes", clientes));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponseMsg("NO HAY CLIENTES", e.getMessage()));
        }
    }
    // obtener un cliente por id
    @GetMapping("/{id}")
    @Operation(summary = "Obtener un cliente por su id asociado", description = "Retorna el cliente asociado a la id proporcionada")
    public ResponseEntity<?> getClientById(@PathVariable Long id){
        try {
            clienteService.getClientById(id);
            return ResponseEntity.ok().body(new ApiResponseMsg("Cliente:", id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error usuario no encontrado" + e.getMessage());
        }
    }

    // Eliminar cliente por ID
    @DeleteMapping("/{id}")
    @Operation(summary = "Borrar un cliente", description = "Borra un cliente asociado a la id proporcionada")
    public ResponseEntity<?> deleteClient(@PathVariable Long id) {
        try {
            clienteService.deleteClient(id);
            return ResponseEntity.ok().body(new ApiResponseMsg("Cliente Eliminado", id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponseMsg("Error: No se pudo eliminar el cliente", e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modificar cliente por su id", description = "Retorna el cliente con sus campos modificados")
    public ResponseEntity<ClienteDTO> updateCliente(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO){
        ClienteDTO updatedCliente = clienteService.updaClienteDTO(id, clienteDTO);
        return ResponseEntity.ok().body(updatedCliente);
    }

}
