package SegundaEntrega.api.model;

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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "cliente_panaderia",
        joinColumns = @JoinColumn(name = "cliente_id"),
        inverseJoinColumns = @JoinColumn(name = "panaderia_id")
    )
    private Set<Panaderia> panaderias;

    public Cliente(){

    }

    public Cliente(Long id, String name, String email, String phone, Set<Panaderia> panaderias) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.panaderias = panaderias;
    }

    public Set<Panaderia> getPanaderias() {
        return panaderias;
    }

    public void setPanaderias(Set<Panaderia> panaderias) {
        this.panaderias = panaderias;
    }
}

