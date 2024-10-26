package SegundaEntrega.api.DTO;

import lombok.Builder;
import lombok.Data;
import java.util.Set;

@Data
@Builder
public class PanaderiaDTO {
    private Long id;
    private String nombre;
    private String direccion;
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