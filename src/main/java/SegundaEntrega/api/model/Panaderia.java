package SegundaEntrega.api.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
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

    @ManyToMany(mappedBy = "panaderias")
    private Set<Cliente> clientes = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
        name = "panaderia_producto",
        joinColumns = @JoinColumn(name = "panaderia_id"),
        inverseJoinColumns = @JoinColumn(name = "producto_id")
    )
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

    public void addProductos(Producto producto){
        if(!this.productos.contains(producto)){
            productos.add(producto);

            if(!producto.getPanaderias().contains(this)){
                producto.getPanaderias().add(this);
            }
        }
    }

    public void addClientes(Cliente cliente){
        if(!this.clientes.contains(cliente)){
            clientes.add(cliente);

            if(!cliente.getPanaderias().contains(this)){
                cliente.getPanaderias().add(this);
            }
        }
    }
    
}


