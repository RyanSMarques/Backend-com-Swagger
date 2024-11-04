package com.atividade9.APIrestful.service;

import com.atividade9.APIrestful.Cliente;
import com.atividade9.APIrestful.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository repository;

    public Cliente salvar(Cliente cliente) {
        if (cliente.getNome().isEmpty()) throw new IllegalArgumentException("Nome não pode ser vazio");
        return repository.save(cliente);
    }

    public List<Cliente> listarTodos() {
        return repository.findAll();
    }

    public List<Cliente> listarPorNome(String nome) {
        return repository.findByNome(nome);
    }

    public Cliente listarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }
}
