package SegundaEntrega.api.model;

import java.util.Set;

import SegundaEntrega.api.services.FechaService;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Cliente {

    //se generan las columnas con el generation type
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String correo;
    private String telefono;
    private String fechaDeCreacion = FechaService.getFechaActual();
    private String fechaDeModificacion;
    private int edad;

    // se genera el constructor

    public Cliente(Long id, String nombre, String correo, String telefono, int edad, Panaderia panaderia) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.edad = edad;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "cliente_panaderia",
        joinColumns = @JoinColumn(name = "cliente_id"),
        inverseJoinColumns = @JoinColumn(name = "panaderia_id")
    )
    private Set<Panaderia> panaderias;

    public void setPanaderias(Panaderia panaderia) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setPanaderias'");
    }


}
