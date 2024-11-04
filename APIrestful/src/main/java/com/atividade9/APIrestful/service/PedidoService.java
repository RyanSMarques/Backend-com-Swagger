package com.atividade9.APIrestful.service;

import com.atividade9.APIrestful.Pedido;
import com.atividade9.APIrestful.repository.ClienteRepository;
import com.atividade9.APIrestful.repository.PedidoRepository;
import com.atividade9.APIrestful.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository repository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public Pedido salvar(Pedido pedido) {
        if (pedido.getIdCliente() == null || !clienteRepository.existsById(pedido.getIdCliente())) {
            throw new IllegalArgumentException("Cliente inválido");
        }

        if (pedido.getIdsProdutos().isEmpty()) {
            throw new IllegalArgumentException("Pedido deve conter pelo menos um produto");
        }

        for (Long idProduto : pedido.getIdsProdutos()) {
            if (!produtoRepository.existsById(idProduto)) {
                throw new IllegalArgumentException("Produto inválido no pedido: " + idProduto);
            }
        }

        return repository.save(pedido);
    }

    public List<Pedido> listarTodos() {
        return repository.findAll();
    }

    public Pedido listarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<Pedido> listarPorIdCliente(Long idCliente) {
        return repository.findByIdCliente(idCliente);
    }

    public List<Pedido> listarPorIdProduto(Long idProduto) {
        return repository.findByIdsProdutos(idProduto);
    }
}

