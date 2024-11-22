package com.atividade9.APIrestful.service;

import com.atividade9.APIrestful.Cliente;
import com.atividade9.APIrestful.Pedido;
import com.atividade9.APIrestful.Produto;
import com.atividade9.APIrestful.repository.ClienteRepository;
import com.atividade9.APIrestful.repository.PedidoRepository;
import com.atividade9.APIrestful.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    // Método para salvar um pedido
    public Pedido salvar(Pedido pedido) {
        // Valida se o cliente existe
        Cliente cliente = clienteRepository.findById(pedido.getCliente().getId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com ID: " + pedido.getCliente().getId()));

        // Valida se os produtos e suas quantidades existem
        List<Produto> produtos = pedido.getProdutos().stream()
                .map(produto -> produtoRepository.findById(produto.getId())
                        .orElseThrow(() -> new RuntimeException("Produto não encontrado com ID: " + produto.getId())))
                .collect(Collectors.toList());

        // Aqui associamos os produtos e as quantidades de forma correta
        pedido.setCliente(cliente);
        pedido.setProdutos(produtos);

        // Salva o pedido
        return pedidoRepository.save(pedido);
    }


    // Método para listar todos os pedidos
    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }

    // Método para buscar pedido por ID
    public Pedido listarPorId(Long id) {
        return pedidoRepository.findById(id).orElse(null);
    }

    // Método para listar pedidos por cliente
    public List<Pedido> listarPorIdCliente(Long idCliente) {
        return pedidoRepository.findAll().stream()
                .filter(pedido -> pedido.getCliente().getId().equals(idCliente))
                .collect(Collectors.toList());
    }

    // Método para listar pedidos por produto
    public List<Pedido> listarPorIdProduto(Long idProduto) {
        return pedidoRepository.findAll().stream()
                .filter(pedido -> pedido.getProdutos().stream()
                        .anyMatch(produto -> produto.getId().equals(idProduto)))
                .collect(Collectors.toList());
    }
    


}

 
