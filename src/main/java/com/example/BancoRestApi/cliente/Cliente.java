package com.example.BancoRestApi.cliente;

import com.example.BancoRestApi.agencia.Agencia;
import com.example.BancoRestApi.movimentacao.Movimentacao;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(length = 10)
    private String conta;

    @Column(length = 50)
    private String nome;

    @Column(length = 100)
    private String endereco;

    @Column(length = 15)
    private String telefone;

    @Column(length = 40)
    private String email;

    private double saldo;

    @ManyToOne
    @JoinColumn(name = "agencia_fk")
    private Agencia agencia;

    @OneToMany(mappedBy = "cliente1")
    private List<Movimentacao> movimentacoes;

    public Cliente() {}

    public Cliente(String conta, String nome, String endereco, String telefone,
                   String email, double saldo, Agencia agencia){
        this.setConta(conta);
        this.setNome(nome);
        this.setEndereco(endereco);
        this.setTelefone(telefone);
        this.setEmail(email);
        this.setSaldo(saldo);
        agencia.addClientes(this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Agencia getAgencia() {
        return agencia;
    }

    public void setAgencia(Agencia agencia) {
        this.agencia = agencia;
    }

    public List<Movimentacao> getMovimentacoes() {
        return movimentacoes;
    }

    public void setMovimentacoes(ArrayList<Movimentacao> movimentacoes) {
        this.movimentacoes = movimentacoes;
    }

    public void addMovimentacoes(Movimentacao movimentacao, boolean clientePrincipal) {
        this.movimentacoes.add(movimentacao);
        if(clientePrincipal) {
            movimentacao.setCliente1(this);
        } else {
            movimentacao.setCliente2(this);
        }

    }

}
