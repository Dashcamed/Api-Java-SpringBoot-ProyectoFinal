package SegundaEntrega.api.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PanaderiaDTO {
    private Long id;
    private String nombre;
    private String direccion;
    private String telefono;
}
