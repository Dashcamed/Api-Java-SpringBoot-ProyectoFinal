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
import SegundaEntrega.api.services.ClienteService;
import utils.ApiResponse;
import java.util.List;

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

    @PostMapping("/createClient")
    public ResponseEntity<?> addClient(@RequestBody Cliente cliente){
        this.clienteService.saveClient(cliente);
        return ResponseEntity.ok().body(new ApiResponse("cliente Creado", cliente));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteClient(@PathVariable("id") Cliente cliente){
        try {
            this.clienteService.deleteClient(null);
        } catch (Exception e) {
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO) {
        Cliente updatedClient = clienteService.updateClient(id, clienteDTO);
        return ResponseEntity.ok(updatedClient);
    }
}
