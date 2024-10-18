package SegundaEntrega.api.model;

import jakarta.persistence.CascadeType;
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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String nombre;
    private Double precio;
    private int stock;
    private String categoria;

    public Producto(Long id, String nombre, Double precio, int stock, String categoria, Panaderia panaderia) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.categoria = categoria;
        this.panaderia = panaderia;
    }
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "panaderia_id")

    private Panaderia panaderia;

    public void setPanaderiaId(Long panaderiaId) {
        throw new UnsupportedOperationException("Unimplemented method 'setPanaderiaId'");
    }

}
