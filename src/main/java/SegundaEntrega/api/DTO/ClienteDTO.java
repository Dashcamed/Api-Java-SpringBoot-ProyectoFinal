package SegundaEntrega.api.DTO;

import java.util.Set;

import SegundaEntrega.api.model.ClientePanaderia;
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
    public void setClientePanaderias(Set<ClientePanaderia> clientePanaderias) {
        throw new UnsupportedOperationException("Unimplemented method 'setClientePanaderias'");
    }
    public Set<ClientePanaderia> getClientePanaderias() {
        throw new UnsupportedOperationException("Unimplemented method 'getClientePanaderias'");
    }
}
