package com.example.BancoRestApi.banco;

import com.example.BancoRestApi.agencia.Agencia;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
public class Banco {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(length = 50)
    private String nome;

    @OneToMany(mappedBy = "banco")
    private ArrayList<Agencia> agencias;

    public Banco(String nome) {
        this.setNome(nome);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<Agencia> getAgencias() {
        return agencias;
    }

    public void setAgencias(ArrayList<Agencia> agencias) {
        this.agencias = agencias;
    }

    public void addAgencias(Agencia agencia) {
        this.agencias.add(agencia);
        agencia.setBanco(this);
    }

}
