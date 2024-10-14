package SegundaEntrega.api.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class ClientePanaderia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "panaderia_id", nullable = false)
    private Panaderia panaderia;

    public ClientePanaderia(Cliente cliente, Panaderia panaderia) {
        this.cliente = cliente;
        this.panaderia = panaderia;
    }
}

