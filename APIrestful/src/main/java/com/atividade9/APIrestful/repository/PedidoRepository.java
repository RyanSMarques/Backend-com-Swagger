package com.atividade9.APIrestful.repository;

import com.atividade9.APIrestful.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByProdutos_Id(Long id);
}

