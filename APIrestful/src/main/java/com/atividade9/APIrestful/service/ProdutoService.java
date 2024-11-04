package com.atividade9.APIrestful.service;

import com.atividade9.APIrestful.Produto;
import com.atividade9.APIrestful.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository repository;

    public Produto salvar(Produto produto) {
        if (produto.getNome().isEmpty()) throw new IllegalArgumentException("Nome não pode ser vazio");
        if (produto.getPreco() < 0) throw new IllegalArgumentException("Preço não pode ser negativo");
        return repository.save(produto);
    }

    public List<Produto> listarTodos() {
        return repository.findAll();
    }

    public Produto listarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<Produto> listarPorNome(String nome) {
        return repository.findByNome(nome);
    }
}
