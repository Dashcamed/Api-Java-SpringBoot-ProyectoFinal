package SegundaEntrega.api.controller;

import java.util.List;
import java.util.Optional;

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

import SegundaEntrega.api.DTO.PanaderiaDTO;
import SegundaEntrega.api.services.PanaderiaService;
import utils.ApiResponse;

@RestController
@RequestMapping("/api/panaderias")
public class PanaderiaController {
    @Autowired
    private PanaderiaService panaderiaService;

    @GetMapping(path ="/all")
    public ResponseEntity<?> getAllPanaderias(){
        try {
            List<PanaderiaDTO> panaderias = panaderiaService.getAllPanaderias();
            return ResponseEntity.ok().body(new ApiResponse("Lista de panaderias", panaderias));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse("No hay panaderias", e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPanaderiaById(@PathVariable("id") Long id){
        try {
            Optional<PanaderiaDTO> panaderia = panaderiaService.getPanaderiaById(id);
            return ResponseEntity.ok(panaderia);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse("usuario no encontrado", e.getMessage()));
        }
    }

    @PostMapping("/createPanaderia")
    public ResponseEntity<PanaderiaDTO> createPanaderia(@RequestBody PanaderiaDTO panaderiaDTO){
        try {
            PanaderiaDTO createdPanaderia = panaderiaService.savePanaderia(panaderiaDTO);
            return new ResponseEntity<>(createdPanaderia, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
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
    public ResponseEntity<Void> updatePanaderia(@PathVariable Long id, @RequestBody PanaderiaDTO panaderiaDTO) {
        try {
            panaderiaService.updatePanaderia(id, panaderiaDTO);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}