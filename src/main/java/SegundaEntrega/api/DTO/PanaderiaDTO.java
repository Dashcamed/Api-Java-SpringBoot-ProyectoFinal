package SegundaEntrega.api.DTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PanaderiaDTO {
    private String nombre;
    private String direccion;
    private String telefono;

    public PanaderiaDTO() {
    }

    public PanaderiaDTO( String nombre, String direccion, String telefono) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }

}