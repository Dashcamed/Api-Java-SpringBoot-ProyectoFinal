package SegundaEntrega.api.DTO;

import java.util.Set;

import SegundaEntrega.api.model.Panaderia;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClienteDTO {
    @Schema(description = "Unique identifier of the user", example = "1")
    private Long id;
    @Schema(description = "Name of the user", example = "John Doe")
    private String name;
    @Schema(description = "Email address of the user", example = "john.doe@example.com")
    private String email;
    @Schema(description = "Phone number of the user", example = "1234567890")
    private String phone;
    @Schema(description = "List of panaderias with the user")
    private Set<Panaderia> panaderias;


    public ClienteDTO() {
    }

    public ClienteDTO(Long id, String name, String email, String phone, Set<Panaderia> panaderias) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.panaderias = panaderias;
    }

}
