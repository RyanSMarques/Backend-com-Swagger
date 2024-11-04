package com.atividade9.APIrestful.repository;

import com.atividade9.APIrestful.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByIdCliente(Long idCliente);
    List<Pedido> findByIdsProdutos(Long idProduto);
}
