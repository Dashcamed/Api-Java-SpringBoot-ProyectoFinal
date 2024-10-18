package SegundaEntrega.api.DTO;

import java.util.Set;

import SegundaEntrega.api.model.ClientePanaderia;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClienteDTO {
    private String nombre;
    private String correo;
    private String telefono;
    private int edad;
    private Long panaderiaId;
    private Set<ClientePanaderia> clientePanaderias;

    public ClienteDTO() {
    }

    public ClienteDTO(String nombre, String correo, String telefono, int edad, Long panaderiaId, Set<ClientePanaderia> clientePanaderias) {
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.edad = edad;
        this.panaderiaId = panaderiaId;
        this.clientePanaderias = clientePanaderias;
    }
}
