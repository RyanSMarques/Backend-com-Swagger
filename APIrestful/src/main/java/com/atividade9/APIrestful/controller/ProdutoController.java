package com.atividade9.APIrestful.controller;

import com.atividade9.APIrestful.Produto;
import com.atividade9.APIrestful.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @PostMapping
    public ResponseEntity<Produto> salvar(@RequestBody Produto produto) {
        Produto novoProduto = service.salvar(produto);
        return new ResponseEntity<>(novoProduto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Produto>> listarTodos() {
        List<Produto> produtos = service.listarTodos();
        return new ResponseEntity<>(produtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> listarPorId(@PathVariable Long id) {
        Produto produto = service.listarPorId(id);
        if (produto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(produto, HttpStatus.OK);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Produto>> listarPorNome(@RequestParam String nome) {
        List<Produto> produtos = service.listarPorNome(nome);
        return new ResponseEntity<>(produtos, HttpStatus.OK);
    }
}
