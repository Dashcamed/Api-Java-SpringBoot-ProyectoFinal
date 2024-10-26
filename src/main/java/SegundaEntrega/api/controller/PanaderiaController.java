package SegundaEntrega.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import SegundaEntrega.api.DTO.PanaderiaCreateDTO;
import SegundaEntrega.api.DTO.PanaderiaDTO;
import SegundaEntrega.api.services.PanaderiaService;
import utils.ApiResponse;

@RestController
@RequestMapping("/api/panaderias")
public class PanaderiaController {
    @Autowired
    private final PanaderiaService panaderiaService;

    public PanaderiaController(PanaderiaService panaderiaService) {
        this.panaderiaService = panaderiaService;
    }

    @GetMapping("/all")
public ResponseEntity<List<PanaderiaDTO>> getAllPanaderias(@RequestParam(value = "includeRelations", defaultValue = "false") boolean includeRelations) {
    return ResponseEntity.ok(panaderiaService.getAllPanaderias(true)); // Aquí puedes forzar includeRelations a true para la prueba
}

    @GetMapping("/{id}")
    public ResponseEntity<?> getPanaderiaById(@PathVariable("id") Long id){
        try {
            Optional<PanaderiaDTO> panaderia = panaderiaService.getPanaderiaById(id, false);
            return ResponseEntity.ok(panaderia);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse("usuario no encontrado", e.getMessage()));
        }
    }

    @PostMapping("/createPanaderia")
    public ResponseEntity<PanaderiaDTO> createPanaderia(@RequestBody PanaderiaCreateDTO panaderiaCreateDTO) {
        PanaderiaDTO createdPanaderia = panaderiaService.savePanaderia(panaderiaCreateDTO);
        return ResponseEntity.ok(createdPanaderia);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable("id") Long id) {
        try {
            panaderiaService.deletePanaderia(id);
            return ResponseEntity.ok().body(new ApiResponse("Panadería eliminada", id));
            } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse("Error: No se pudo eliminar la panadería", e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PanaderiaDTO> updatePanaderia(@PathVariable Long id, @RequestBody PanaderiaCreateDTO panaderiaCreateDTO) {
        PanaderiaDTO updatedPanaderia = panaderiaService.updatePanaderia(id, panaderiaCreateDTO);
        return ResponseEntity.ok(updatedPanaderia);
    }
}