package SegundaEntrega.api.DTO;

import lombok.Builder;
import lombok.Data;
import java.util.Set;

import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Builder
public class PanaderiaDTO {
    @Schema(description = "Id of the bakery", example = "1")
    private Long id;
    @Schema(description = "Name of the bakery", example = "Panaderia")
    private String nombre;
    @Schema(description = "Address of the bakery", example = "AV 123")
    private String direccion;
    @Schema(description = "Phone of the bakery", example = "99887766")
    private String telefono;
    private Set<ClienteDTO> clientes;
    private Set<ProductoDTO> productos;

    public PanaderiaDTO() {
    }

    public PanaderiaDTO(Long id, String nombre, String direccion, String telefono,Set<ClienteDTO> clientes, Set<ProductoDTO> productos) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.clientes = clientes;
        this.productos = productos;
    }

}