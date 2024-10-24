package SegundaEntrega.api.model;

import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;

    @ManyToMany
    @JoinTable(
        name = "cliente_panaderia",
        joinColumns = @JoinColumn(name = "cliente_id"),
        inverseJoinColumns = @JoinColumn(name = "panaderia_id")
    )

    private Set<Panaderia> panaderias = new HashSet<>();

    public Cliente(){

    }

    public Cliente(Long id, String name, String email, String phone, Set<Panaderia> panaderias) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.panaderias = panaderias;
    }

    public void addPanaderias(Panaderia panaderia) {
        if (!this.panaderias.contains(panaderia)) {
            panaderias.add(panaderia);
            // Evitar agregar el usuario de vuelta al panaderia para prevenir recursión o
            // ciclos
            if (!panaderia.getClientes().contains(this)) {
                panaderia.getClientes().add(this);
            }
        }
    }
}

