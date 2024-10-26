package SegundaEntrega.api.model;

import java.util.Set;
import jakarta.persistence.*;
import jakarta.persistence.ManyToMany;
import lombok.Builder;
import lombok.Data;


@Entity
@Data
@Builder
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private Double precio;
    private int stock;
    private String categoria;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
    name = "producto_panaderia",
    joinColumns = @JoinColumn(name = "producto_id"),
    inverseJoinColumns = @JoinColumn(name = "panaderia_id")
)
    private Set<Panaderia> panaderias;

    public Producto(){

    }

    public Producto(Long id, String nombre, Double precio, int stock, String categoria, Set<Panaderia> panaderias) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.categoria = categoria;
        this.panaderias = panaderias;
    }

    public Set<Panaderia> getPanaderias() {
        return panaderias;
    }

    public void setPanaderias(Set<Panaderia> panaderias) {
        this.panaderias = panaderias;
    }

    

}
