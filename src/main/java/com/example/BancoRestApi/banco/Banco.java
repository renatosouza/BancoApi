package com.example.BancoRestApi.banco;

import com.example.BancoRestApi.agencia.Agencia;

import java.util.ArrayList;

public class Banco {

    private int id;
    private String nome;
    private ArrayList<Agencia> agencias;

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

}
