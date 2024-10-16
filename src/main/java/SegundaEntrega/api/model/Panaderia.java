package SegundaEntrega.api.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

// import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Panaderia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String direccion;
    private String telefono;

    // @JsonManagedReference
    @OneToMany(mappedBy = "panaderia", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Producto> productos;

    @OneToMany(mappedBy = "panaderia", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<ClientePanaderia> clientePanaderias = new HashSet<>();
}
