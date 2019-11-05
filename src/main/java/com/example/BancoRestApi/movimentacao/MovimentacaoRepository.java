package com.example.BancoRestApi.movimentacao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Integer> {

    List<Movimentacao> findByCliente1_id(Integer cliente1_id);

}
