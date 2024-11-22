package com.atividade9.APIrestful;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne // Relacionamento muitos-para-um com Cliente
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToMany // Relacionamento muitos-para-muitos com Produto
    @JoinTable(
        name = "pedido_produto",
        joinColumns = @JoinColumn(name = "pedido_id"),
        inverseJoinColumns = @JoinColumn(name = "produto_id")
    )
    private List<Produto> produtos;

    // Nova lista para armazenar a quantidade dos produtos
    @ElementCollection
    @CollectionTable(name = "pedido_produto_quantidade", joinColumns = @JoinColumn(name = "pedido_id"))
    @Column(name = "quantidade")
    private List<Integer> quantidades;
}



