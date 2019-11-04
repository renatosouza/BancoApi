package com.example.BancoRestApi.movimentacao;

import com.example.BancoRestApi.cliente.Cliente;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Movimentacao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    //1-saque, 2-deposito ou 3-transferencia
    private int tipo;
    private double valor;

    @Temporal(TemporalType.TIMESTAMP)
    private Date horario;

    @ManyToOne
    @JoinColumn(name = "cliente1_fk")
    private Cliente cliente1;

    //necessario se transferencia
    private Cliente cliente2;

    public Movimentacao(int tipo, double valor, Date horario,
                        Cliente cliente1, Cliente cliente2){
        this.setTipo(tipo);
        this.setValor(valor);
        this.setHorario(horario);
        cliente1.addMovimentacoes(this, true);
        if(cliente2 != null) {
            cliente2.addMovimentacoes(this, false);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Cliente getCliente1() {
        return cliente1;
    }

    public Date getHorario() {
        return horario;
    }

    public void setHorario(Date horario) {
        this.horario = horario;
    }

    public void setCliente1(Cliente cliente1) {
        this.cliente1 = cliente1;
    }

    public Cliente getCliente2() {
        return cliente2;
    }

    public void setCliente2(Cliente cliente2) {
        this.cliente2 = cliente2;
    }

}
