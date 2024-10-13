package SegundaEntrega.api.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClienteDTO {
    private String nombre;
    private String correo;
    private String telefono;
    private int edad;
    private Long panaderiaId;
}
