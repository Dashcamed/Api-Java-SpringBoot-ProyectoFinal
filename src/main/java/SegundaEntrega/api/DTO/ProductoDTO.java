package SegundaEntrega.api.DTO;

import java.util.Set;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductoDTO {
    private String nombre;
    private Double precio;
    private int stock;
    private String categoria;
    private Set<Long> panaderiaIds;

    ProductoDTO(){

    }

    public ProductoDTO(String nombre, Double precio, int stock, String categoria, Set<Long> panaderiaIds) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.categoria = categoria;
        this.panaderiaIds = panaderiaIds;
    }

    public Set<Long> getPanaderiaIds() {
        return panaderiaIds;
    }

    public void setPanaderiaIds(Set<Long> panaderiaIds) {
        this.panaderiaIds = panaderiaIds;
    }
}
