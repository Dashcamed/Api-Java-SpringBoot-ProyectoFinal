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
import org.springframework.web.bind.annotation.RestController;

import SegundaEntrega.api.DTO.PanaderiaDTO;
import SegundaEntrega.api.model.Panaderia;
import SegundaEntrega.api.services.PanaderiaService;
import utils.ApiResponse;

@RestController
@RequestMapping("/api/panaderias")
public class PanaderiaController {
    @Autowired
    private PanaderiaService panaderiaService;

    @GetMapping
    public ResponseEntity<?> getAllPanaderias(){
        List<Panaderia> panaderias = panaderiaService.getAllPanaderias(null);
        return ResponseEntity.ok().body(new ApiResponse("Lista de panaderias", panaderias));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPanaderiaById(@PathVariable("id") Long id){
        Optional<Panaderia> panaderia = panaderiaService.getPanaderiaById(id);
        return ResponseEntity.ok().body(new ApiResponse("Esta es la panaderia", panaderia));
    }

    @PostMapping("/createPanaderia")
    public ResponseEntity<?> createPanaderia(@RequestBody Panaderia panaderia){
        this.panaderiaService.savePanaderia(panaderia);
        return ResponseEntity.ok().body(new ApiResponse("Panaderia Creada", panaderia));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable("id") Long id) {
    try {
        panaderiaService.deletePanaderia(id);
        return ResponseEntity.ok().body(new ApiResponse("Panadería eliminada", null));
    } catch (Exception e) {
        return ResponseEntity.badRequest().body(new ApiResponse("Error: No se pudo eliminar la panadería", null));
    }
}

    @PutMapping("/{id}")
    public ResponseEntity<Panaderia> updatePanaderia(@PathVariable Long id, @RequestBody PanaderiaDTO panaderiaDTO) {
        Panaderia updatedPanaderia = panaderiaService.updatePanaderia(id, panaderiaDTO);
        return ResponseEntity.ok(updatedPanaderia);
    }
}