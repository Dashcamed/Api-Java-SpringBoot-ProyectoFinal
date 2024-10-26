package SegundaEntrega.api.model;

import java.util.Set;
import java.util.HashSet;
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
public class Panaderia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String direccion;
    private String telefono;

    @ManyToMany(mappedBy = "panaderias", fetch = FetchType.LAZY)
    private Set<Cliente> clientes = new HashSet<>();

    @ManyToMany(mappedBy = "panaderias", fetch = FetchType.LAZY)
    private Set<Producto> productos = new HashSet<>();

    public Panaderia(){

    }

    public Panaderia(Long id, String nombre, String direccion, String telefono, Set<Cliente> clientes,
            Set<Producto> productos) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.clientes = clientes;
        this.productos = productos;
    }

}


