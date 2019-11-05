package com.example.BancoRestApi.movimentacao;

import com.example.BancoRestApi.agencia.Agencia;
import com.example.BancoRestApi.agencia.AgenciaRepository;
import com.example.BancoRestApi.banco.Banco;
import com.example.BancoRestApi.banco.BancoRepository;
import com.example.BancoRestApi.cliente.Cliente;
import com.example.BancoRestApi.cliente.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
public class MovimentacaoController {

    @Autowired
    MovimentacaoRepository movimentacaoRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    AgenciaRepository agenciaRepository;

    @Autowired
    BancoRepository bancoRepository;

    @GetMapping("/bancos/{bancoId}/agencias/{agenciaId}/clientes/{clienteId}/extrato")
    public List<Movimentacao> index(@PathVariable Integer bancoId,
                                    @PathVariable Integer agenciaId,
                                    @PathVariable Integer clienteId) {
        Banco banco = bancoRepository.findById(bancoId).orElse(null);
        if(banco == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Banco não existe!");
        }

        Agencia agencia = agenciaRepository.findById(agenciaId).orElse(null);
        if(agencia == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Agência não existe!");
        }

        Cliente cliente = clienteRepository.findById(clienteId).orElse(null);
        if(cliente == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Cliente não existe!");
        }

        return movimentacaoRepository.findByCliente1_id(clienteId);
    }


    @PostMapping("/bancos/{bancoId}/agencias/{agenciaId}/clientes/{clienteId}/saque")
    public Movimentacao withdraw(@PathVariable Integer bancoId,
                                 @PathVariable Integer agenciaId,
                                 @PathVariable Integer clienteId,
                                 @RequestBody Map<String, String> body) {
        Banco banco = bancoRepository.findById(bancoId).orElse(null);
        if(banco == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Banco não existe!");
        }

        Agencia agencia = agenciaRepository.findById(agenciaId).orElse(null);
        if(agencia == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Agência não existe!");
        }

        Cliente cliente = clienteRepository.findById(clienteId).orElse(null);
        if(cliente == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Cliente não existe!");
        }

        double valor = Double.parseDouble(body.get("valor"));
        double valorReal = (valor < 0) ? valor : -valor;
        double valorAbsoluto = (valor < 0) ? -valor : valor;
        Date horario = new Date(System.currentTimeMillis());

        if(cliente.getSaldo() < valorAbsoluto) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                              "Saldo suficiente para saque!");
        }
        cliente.setSaldo(cliente.getSaldo()-valorAbsoluto);
        cliente = clienteRepository.save(cliente);

        return movimentacaoRepository.save(new Movimentacao(1, valorReal, horario,
                                                            cliente, null));
    }


    @PostMapping("/bancos/{bancoId}/agencias/{agenciaId}/clientes/{clienteId}/deposito")
    public Movimentacao deposit(@PathVariable Integer bancoId,
                                @PathVariable Integer agenciaId,
                                @PathVariable Integer clienteId,
                                @RequestBody Map<String, String> body) {
        Banco banco = bancoRepository.findById(bancoId).orElse(null);
        if(banco == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Banco não existe!");
        }

        Agencia agencia = agenciaRepository.findById(agenciaId).orElse(null);
        if(agencia == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Agência não existe!");
        }

        Cliente cliente = clienteRepository.findById(clienteId).orElse(null);
        if(cliente == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Cliente não existe!");
        }

        double valor = Double.parseDouble(body.get("valor"));
        valor = (valor > 0) ? valor : -valor;
        Date horario = new Date(System.currentTimeMillis());

        cliente.setSaldo(cliente.getSaldo()+valor);
        cliente = clienteRepository.save(cliente);

        return movimentacaoRepository.save(new Movimentacao(2, valor, horario,
                                                            cliente, null));
    }

    @PostMapping("/bancos/{bancoId}/agencias/{agenciaId}/clientes/{clienteId}/transferencia")
    public Movimentacao transfer(@PathVariable Integer bancoId,
                                 @PathVariable Integer agenciaId,
                                 @PathVariable Integer clienteId,
                                 @RequestBody Map<String, String> body) {
        Banco banco = bancoRepository.findById(bancoId).orElse(null);
        if(banco == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Banco não existe!");
        }

        Agencia agencia = agenciaRepository.findById(agenciaId).orElse(null);
        if(agencia == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Agência não existe!");
        }

        Cliente clientePagador = clienteRepository.findById(clienteId).orElse(null);
        if(clientePagador == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Cliente não existe!");
        }

        int destinatario = Integer.parseInt(body.get("destinatario"));
        Cliente clienteRecebedor = clienteRepository.findById(destinatario).orElse(null);
        if(clienteRecebedor == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Destinatário não existe!");
        }

        double valor = Double.parseDouble(body.get("valor"));
        double valorDeduzir = (valor < 0) ? valor : -valor;
        double valorCreditar = (valor > 0) ? valor : -valor;
        Date horario = new Date(System.currentTimeMillis());

        if(clientePagador.getSaldo() < valorCreditar) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Saldo insuficiente pra fazer transferência!");
        }

        clientePagador.setSaldo(clientePagador.getSaldo()-valorCreditar);
        clientePagador = clienteRepository.save(clientePagador);
        clienteRecebedor.setSaldo(clienteRecebedor.getSaldo()+valorCreditar);
        clienteRecebedor = clienteRepository.save(clienteRecebedor);

        Movimentacao movimentacaoRecebedor = movimentacaoRepository
                .save(new Movimentacao(3, valorCreditar, horario,
                        clienteRecebedor, clientePagador));

        return movimentacaoRepository.save(new Movimentacao(3, valorDeduzir,
                horario, clientePagador, clienteRecebedor));
    }

}
