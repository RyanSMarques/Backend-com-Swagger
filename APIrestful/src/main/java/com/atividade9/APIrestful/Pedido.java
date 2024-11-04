package com.atividade9.APIrestful;

import jakarta.persistence.*;
import java.util.List;
import lombok.Data;

@Entity
@Data
public class Pedido {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idCliente;

    @ElementCollection
    private List<Long> idsProdutos;
}
