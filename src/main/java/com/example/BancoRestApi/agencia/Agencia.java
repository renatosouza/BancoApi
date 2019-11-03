package com.example.BancoRestApi.agencia;

import com.example.BancoRestApi.banco.Banco;
import com.example.BancoRestApi.cliente.Cliente;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
public class Agencia {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(length = 10)
    private String codigo;

    @Column(length = 100)
    private String endereco;

    @Column(length = 15)
    private String telefone;

    @ManyToOne
    @JoinColumn(name = "banco_fk")
    private Banco banco;

    @OneToMany(mappedBy = "cliente")
    private ArrayList<Cliente> clientes;

    public Agencia(String codigo, Banco banco) {
        this.setCodigo(codigo);
        this.setBanco(banco);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }

    public void addClientes(Cliente cliente) {
        this.clientes.add(cliente);
        cliente.setAgencia(this);
    }

}
