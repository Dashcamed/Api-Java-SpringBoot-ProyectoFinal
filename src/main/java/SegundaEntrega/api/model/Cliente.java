package SegundaEntrega.api.model;

import java.util.HashSet;
import java.util.Set;

import SegundaEntrega.api.services.FechaService;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String correo;
    private String telefono;
    private String fechaDeCreacion = FechaService.getFechaActual();
    private String fechaDeModificacion;
    private int edad;

    // Cambia a Set de ClientePanaderia
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<ClientePanaderia> clientePanaderias = new HashSet<>();

    public void addPanaderia(Panaderia panaderia) {
        ClientePanaderia clientePanaderia = new ClientePanaderia(this, panaderia);
        this.clientePanaderias.add(clientePanaderia);
    }

    public void setClientePanaderias(Set<ClientePanaderia> clientePanaderias) {
        this.clientePanaderias = clientePanaderias;
    }
}
