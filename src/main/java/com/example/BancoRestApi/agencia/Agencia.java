package com.example.BancoRestApi.agencia;

import com.example.BancoRestApi.banco.Banco;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"codigo", "banco_fk"}))
public class Agencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 10)
    private String codigo;

    @Column(length = 100)
    private String endereco;

    @Column(length = 15)
    private String telefone;

    @ManyToOne
    @JoinColumn(name = "banco_fk")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Banco banco;

    public Agencia() {}

    public Agencia(String codigo, String endereco, String telefone, Banco banco) {
        this.setCodigo(codigo);
        this.setEndereco(endereco);
        this.setTelefone(telefone);
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

}
