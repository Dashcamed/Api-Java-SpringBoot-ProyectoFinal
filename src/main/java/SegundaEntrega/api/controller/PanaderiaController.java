package SegundaEntrega.api.controller;

import java.util.List;

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
        List<Panaderia> panaderias = panaderiaService.getAllPanaderias();
        return ResponseEntity.ok().body(new ApiResponse(null, panaderias));
    }

    @PostMapping("/createPanaderia")
    public ResponseEntity<?> createPanaderia(@RequestBody Panaderia panaderia){
        this.panaderiaService.savePanaderia(null);
        return ResponseEntity.ok().body(new ApiResponse("Panaderia Creada", panaderia));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteClient(@PathVariable("id") Panaderia panaderia){
        try {
            this.panaderiaService.deletePanaderia(null);
        } catch (Exception e) {
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Panaderia> updatePanaderia(@PathVariable Long id, @RequestBody PanaderiaDTO panaderiaDTO) {
        Panaderia updatedPanaderia = panaderiaService.updatePanaderia(id, panaderiaDTO);
        return ResponseEntity.ok(updatedPanaderia);
    }
}