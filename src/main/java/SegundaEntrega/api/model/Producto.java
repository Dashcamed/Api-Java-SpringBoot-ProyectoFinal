package SegundaEntrega.api.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    @ManyToMany(mappedBy = "productos", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Panaderia> panaderias = new HashSet<>();

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

    public void addPanaderias(Panaderia panaderia) {
        if (!this.panaderias.contains(panaderia)) {
            panaderias.add(panaderia);
            // Evitar agregar el usuario de vuelta al panaderia para prevenir recursión o
            // ciclos
            if (!panaderia.getProductos().contains(this)) {
                panaderia.getProductos().add(this);
            }
        }
    }

}
