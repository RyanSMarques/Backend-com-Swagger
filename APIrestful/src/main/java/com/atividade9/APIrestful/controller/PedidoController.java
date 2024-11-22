package com.atividade9.APIrestful.controller;

import com.atividade9.APIrestful.Pedido;
import com.atividade9.APIrestful.Cliente;
import com.atividade9.APIrestful.Produto;
import com.atividade9.APIrestful.repository.ClienteRepository;
import com.atividade9.APIrestful.repository.PedidoRepository;
import com.atividade9.APIrestful.repository.ProdutoRepository;
import com.atividade9.APIrestful.service.PedidoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService service;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    // Endpoint para salvar um pedido
    @PostMapping
    public ResponseEntity<Pedido> salvar(@RequestBody Pedido pedido) {
        try {
            // Valida se o cliente existe
            Cliente cliente = clienteRepository.findById(pedido.getCliente().getId())
                    .orElseThrow(() -> new RuntimeException("Cliente não encontrado com ID: " + pedido.getCliente().getId()));

            // Valida se os produtos existem e associa-los ao pedido
            List<Produto> produtos = produtoRepository.findAllById(pedido.getProdutos().stream()
                    .map(Produto::getId)
                    .toList());

            // Associa o cliente e os produtos ao pedido
            pedido.setCliente(cliente);
            pedido.setProdutos(produtos);

            // Salva o pedido
            Pedido novoPedido = pedidoRepository.save(pedido);

            return new ResponseEntity<>(novoPedido, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Endpoint para listar todos os pedidos
    @GetMapping
    public ResponseEntity<List<Pedido>> listarTodos() {
        List<Pedido> pedidos = service.listarTodos();
        return new ResponseEntity<>(pedidos, HttpStatus.OK);
    }

    // Endpoint para buscar pedido por ID
    @GetMapping("/{id}")
    public ResponseEntity<Pedido> listarPorId(@PathVariable Long id) {
        Pedido pedido = service.listarPorId(id);
        if (pedido == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(pedido, HttpStatus.OK);
    }

    // Endpoint para buscar pedidos de um cliente específico
    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<List<Pedido>> listarPorIdCliente(@PathVariable Long idCliente) {
        List<Pedido> pedidos = service.listarPorIdCliente(idCliente);
        return new ResponseEntity<>(pedidos, HttpStatus.OK);
    }

    // Endpoint para buscar pedidos de um produto específico
    @GetMapping("/produto/{idProduto}")
    public ResponseEntity<List<Pedido>> listarPorIdProduto(@PathVariable Long idProduto) {
        List<Pedido> pedidos = service.listarPorIdProduto(idProduto);
        return new ResponseEntity<>(pedidos, HttpStatus.OK);
    }
}
