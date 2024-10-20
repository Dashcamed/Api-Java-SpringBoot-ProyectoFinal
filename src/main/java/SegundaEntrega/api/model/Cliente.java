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
    private String name;
    private String email;
    private String phone;
    private String fechaDeCreacion = FechaService.getFechaActual();
    
    @Column(nullable = true)  // Permitir que sea opcional
    private String fechaDeModificacion;

    @Column(nullable = true)  // Si no es obligatorio, puede ser null
    private Integer edad; // Se cambia a Integer para poder manejar null

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<ClientePanaderia> clientePanaderias = new HashSet<>();

    public void addPanaderia(Panaderia panaderia) {
        ClientePanaderia clientePanaderia = new ClientePanaderia(this, panaderia);
        this.clientePanaderias.add(clientePanaderia);
    }

    public void setClientePanaderias(Set<ClientePanaderia> clientePanaderias) {
        this.clientePanaderias = clientePanaderias;
    }

    public Object getPanaderia() {
        throw new UnsupportedOperationException("Unimplemented method 'getPanaderia'");
    }
}
