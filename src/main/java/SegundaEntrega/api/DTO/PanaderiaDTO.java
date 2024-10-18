package SegundaEntrega.api.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PanaderiaDTO {
    private Long id;
    private String nombre;
    private String direccion;
    private String telefono;

    public PanaderiaDTO(){

    }

    public PanaderiaDTO(Long id, String nombre, String direccion, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }

}
