package SegundaEntrega.api.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;


@Entity
@Data
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private Double precio;
    private int stock;
    private String categoria;

    @ManyToMany(fetch = FetchType.LAZY)
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
