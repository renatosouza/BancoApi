package com.example.BancoRestApi.movimentacao;

import com.example.BancoRestApi.cliente.Cliente;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Movimentacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //1-saque, 2-deposito ou 3-transferencia
    private int tipo;
    private double valor;

    @Temporal(TemporalType.TIMESTAMP)
    private Date horario;

    @ManyToOne
    @JoinColumn(name = "cliente1_fk")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Cliente cliente1;

    //necessario se transferencia
    @ManyToOne
    @JoinColumn(name = "cliente2_fk")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Cliente cliente2;

    public Movimentacao() {}

    public Movimentacao(int tipo, double valor, Date horario,
                        Cliente cliente1, Cliente cliente2){
        this.setTipo(tipo);
        this.setValor(valor);
        this.setHorario(horario);
        this.setCliente1(cliente1);
        this.setCliente2(cliente2);
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

    public Date getHorario() {
        return horario;
    }

    public void setHorario(Date horario) {
        this.horario = horario;
    }

    public Cliente getCliente1() {
        return cliente1;
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
