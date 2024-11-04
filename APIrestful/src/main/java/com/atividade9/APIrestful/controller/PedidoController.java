package com.atividade9.APIrestful.controller;

import com.atividade9.APIrestful.Pedido;
import com.atividade9.APIrestful.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    @Autowired
    private PedidoService service;

    @PostMapping
    public Pedido salvar(@RequestBody Pedido pedido) {
        return service.salvar(pedido);
    }

    @GetMapping
    public List<Pedido> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public Pedido listarPorId(@PathVariable Long id) {
        return service.listarPorId(id);
    }

    @GetMapping("/cliente/{idCliente}")
    public List<Pedido> listarPorIdCliente(@PathVariable Long idCliente) {
        return service.listarPorIdCliente(idCliente);
    }

    @GetMapping("/produto/{idProduto}")
    public List<Pedido> listarPorIdProduto(@PathVariable Long idProduto) {
        return service.listarPorIdProduto(idProduto);
    }
}

