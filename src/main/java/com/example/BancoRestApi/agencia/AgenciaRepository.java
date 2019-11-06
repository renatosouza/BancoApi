package com.example.BancoRestApi.agencia;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgenciaRepository extends JpaRepository<Agencia, Integer> {

    List<Agencia> findByBanco_id(Integer bancoId);

    Agencia findByIdAndBanco_id(Integer agenciaId, Integer bancoId);

}
