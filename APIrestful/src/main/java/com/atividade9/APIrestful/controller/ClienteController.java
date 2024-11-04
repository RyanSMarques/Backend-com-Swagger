package com.atividade9.APIrestful.controller;


import com.atividade9.APIrestful.Cliente;
import com.atividade9.APIrestful.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteService service;

    @PostMapping
    public Cliente salvar(@RequestBody Cliente cliente) {
        return service.salvar(cliente);
    }

    @GetMapping
    public List<Cliente> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public Cliente listarPorId(@PathVariable Long id) {
        return service.listarPorId(id);
    }

    @GetMapping("/buscar")
    public List<Cliente> listarPorNome(@RequestParam String nome) {
        return service.listarPorNome(nome);
    }
}
