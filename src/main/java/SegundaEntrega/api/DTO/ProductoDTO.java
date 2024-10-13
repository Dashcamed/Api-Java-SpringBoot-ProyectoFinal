package SegundaEntrega.api.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductoDTO {
    private String nombre;
    private Double precio;
    private int stock;
    private String categoria;
    private Long panaderiaId;
}
