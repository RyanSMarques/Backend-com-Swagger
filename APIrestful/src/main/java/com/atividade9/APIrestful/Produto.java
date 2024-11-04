package com.atividade9.APIrestful;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Produto {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private double preco;
}

