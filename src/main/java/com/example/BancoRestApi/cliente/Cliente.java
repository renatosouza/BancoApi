package com.example.BancoRestApi.cliente;

import com.example.BancoRestApi.agencia.Agencia;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"conta", "agencia_fk"}))
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Agencia agencia;

    public Cliente() {}

    public Cliente(String conta, String nome, String endereco, String telefone,
                   String email, double saldo, Agencia agencia){
        this.setConta(conta);
        this.setNome(nome);
        this.setEndereco(endereco);
        this.setTelefone(telefone);
        this.setEmail(email);
        this.setSaldo(saldo);
        this.setAgencia(agencia);
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

}
