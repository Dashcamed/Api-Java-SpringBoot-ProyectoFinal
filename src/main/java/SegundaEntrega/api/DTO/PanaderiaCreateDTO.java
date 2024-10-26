package SegundaEntrega.api.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PanaderiaCreateDTO {
    private Long id;
    @Schema(description = "Name of the bakery", example = "Panaderia")
    private String nombre;
    @Schema(description = "Name of the user", example = "Av 123")
    private String direccion;
    @Schema(description = "Name of the user", example = "99887766")
    private String telefono;

    public PanaderiaCreateDTO() {
    }

    public PanaderiaCreateDTO(Long id, String nombre, String direccion, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }
}
