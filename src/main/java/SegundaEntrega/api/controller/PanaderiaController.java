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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import utils.ApiResponseMsg;

@RestController
@RequestMapping("/api/panaderias")
public class PanaderiaController {
    @Autowired
    private final PanaderiaService panaderiaService;

    public PanaderiaController(PanaderiaService panaderiaService) {
        this.panaderiaService = panaderiaService;
    }

    @GetMapping("/all")
    @Operation(summary = "Obtener todas las panaderias", description = "Retorna todas las panaderias")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = PanaderiaDTO.class))),
            @ApiResponse(responseCode = "404", description = "Bakeries not found", content = @Content(schema = @Schema(implementation = ApiResponse.class), examples = {
                    @ExampleObject(name = "PanaderiaNotFound", value = "{\"message\": \"Bakery not found\"}", description = "Panaderias no encontradas")
            }))
    })
    public ResponseEntity<List<PanaderiaDTO>> getAllPanaderias(@RequestParam(value = "includeRelations", defaultValue = "false") boolean includeRelations) {
        return ResponseEntity.ok(panaderiaService.getAllPanaderias(true)); // Aquí puedes forzar includeRelations a true para la prueba
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener una panaderia por su id", description = "Retorna la panaderia asociada al id y sus relaciones")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = PanaderiaDTO.class))),
        @ApiResponse(responseCode = "404", description = "Bakery not found", content = @Content(schema = @Schema(implementation = ApiResponse.class), examples = {
                @ExampleObject(name = "PanaderiaNotFound", value = "{\"message\": \"Bakery not found\"}", description = "Panaderia no encontrada")
        }))
    })
    public ResponseEntity<?> getPanaderiaById(@PathVariable("id") Long id){
        try {
            Optional<PanaderiaDTO> panaderia = panaderiaService.getPanaderiaById(id, false);
            return ResponseEntity.ok(panaderia);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponseMsg("usuario no encontrado", e.getMessage()));
        }
    }

    @PostMapping("/createPanaderia")
    @Operation(summary = "Crear una panaderia", description = "Retorna la panaderia creada")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = PanaderiaCreateDTO.class))),
        @ApiResponse(responseCode = "404", description = "Bakery not created", content = @Content(schema = @Schema(implementation = ApiResponse.class), examples = {
                @ExampleObject(name = "PanaderiaNotCreated", value = "{\"message\": \"Bakery not created\"}", description = "Panaderia no se pudo crear")
        }))
    })
    public ResponseEntity<PanaderiaDTO> createPanaderia(@RequestBody PanaderiaCreateDTO panaderiaCreateDTO) {
        PanaderiaDTO createdPanaderia = panaderiaService.savePanaderia(panaderiaCreateDTO);
        return ResponseEntity.ok(createdPanaderia);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Borra una panaderia por su id asociado", description = "Retorna mensaje Panaderia eliminada")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = PanaderiaDTO.class))),
        @ApiResponse(responseCode = "404", description = "Bakery could not be deleted", content = @Content(schema = @Schema(implementation = ApiResponse.class), examples = {
                @ExampleObject(name = "PanaderiaNotDeleted", value = "{\"message\": \"Bakery not deleted\"}", description = "Panaderia no borrada")
        }))
    })
    public ResponseEntity<?> deleteClient(@PathVariable("id") Long id) {
        try {
            panaderiaService.deletePanaderia(id);
            return ResponseEntity.ok().body(new ApiResponseMsg("Panadería eliminada", id));
            } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponseMsg("Error: No se pudo eliminar la panadería", e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifica los campos de una panaderia por su id", description = "Retorna la panaderia modificada.")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = PanaderiaDTO.class))),
        @ApiResponse(responseCode = "404", description = "Bakery not found", content = @Content(schema = @Schema(implementation = ApiResponse.class), examples = {
                @ExampleObject(name = "PanaderiaNotFound", value = "{\"message\": \"Bakery not found\"}", description = "Panaderia no encontrada ni modificada")
        }))
    })
    public ResponseEntity<PanaderiaDTO> updatePanaderia(@PathVariable Long id, @RequestBody PanaderiaCreateDTO panaderiaCreateDTO) {
        PanaderiaDTO updatedPanaderia = panaderiaService.updatePanaderia(id, panaderiaCreateDTO);
        return ResponseEntity.ok(updatedPanaderia);
    }
}