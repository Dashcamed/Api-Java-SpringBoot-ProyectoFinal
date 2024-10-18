package SegundaEntrega.api.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductoDTO {
    private String nombre;
    private Double precio;
    private int stock;
    private String categoria;
    private Long panaderiaId;

    public ProductoDTO(){

    }

    public ProductoDTO(String nombre, Double precio, int stock, String categoria, Long panaderiaId) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.categoria = categoria;
        this.panaderiaId = panaderiaId;
    }

}
