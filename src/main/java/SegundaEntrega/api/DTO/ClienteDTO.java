package SegundaEntrega.api.DTO;

import java.util.Set;

import SegundaEntrega.api.model.ClientePanaderia;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClienteDTO {

    private String name;
    private String email;
    private String phone;
    private Long panaderiaId;
    private Set<ClientePanaderia> clientePanaderias;

    public ClienteDTO() {
    }

    public ClienteDTO(String name, String email, String phone, Long panaderiaId, Set<ClientePanaderia> clientePanaderias) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.panaderiaId = panaderiaId;
        this.clientePanaderias = clientePanaderias;
    }

    public void addPanaderia(PanaderiaDTO panaderiaDTO) {
        throw new UnsupportedOperationException("Unimplemented method 'addPanaderia'");
    }
}
