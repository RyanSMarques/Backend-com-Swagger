package com.atividade9.APIrestful.controller;


import com.atividade9.APIrestful.Produto;
import com.atividade9.APIrestful.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    @Autowired
    private ProdutoService service;

    @PostMapping
    public Produto salvar(@RequestBody Produto produto) {
        return service.salvar(produto);
    }

    @GetMapping
    public List<Produto> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public Produto listarPorId(@PathVariable Long id) {
        return service.listarPorId(id);
    }

    @GetMapping("/buscar")
    public List<Produto> listarPorNome(@RequestParam String nome) {
        return service.listarPorNome(nome);
    }
}
