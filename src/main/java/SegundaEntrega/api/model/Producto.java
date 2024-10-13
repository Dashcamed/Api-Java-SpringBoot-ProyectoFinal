package SegundaEntrega.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Producto {
    //se generan las columnas con el generation type
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String nombre;
    private Double precio;
    private int stock;
    private String categoria;

    // se genera el constructor
    public Producto(Long id, String nombre, Double precio, int stock, String categoria, Panaderia panaderia) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.categoria = categoria;
        this.panaderia = panaderia;
    }

    // se genera la relacion en este caso muchos productos pertenecen a una panaderia y el join para unir a la panaderia
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "panaderia_id")
    private Panaderia panaderia;

}
