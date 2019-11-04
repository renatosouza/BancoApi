package com.example.BancoRestApi.movimentacao;

import com.example.BancoRestApi.cliente.Cliente;
import com.example.BancoRestApi.cliente.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
public class MovimentacaoController {

    @Autowired
    MovimentacaoRepository movimentacaoRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @GetMapping("/bancos/{bancoId}/agencias/{agenciaId}/clientes/{clienteId}/extrato")
    public List<Movimentacao> index(@PathVariable String clienteId) {
        int cliente_id = Integer.parseInt(clienteId);
        Cliente cliente = clienteRepository.findById(cliente_id).orElse(null);
        return cliente.getMovimentacoes();
    }

    @PostMapping("/bancos/{bancoId}/agencias/{agenciaId}/clientes/{clienteId}/saque")
    public Movimentacao withdraw(@PathVariable String clienteId,
                                 @RequestBody Map<String, String> body) {
        double valor = Double.parseDouble(body.get("valor"));
        valor = (valor < 0) ? valor : -valor;
        Date horario = new Date(System.currentTimeMillis());

        int cliente_id = Integer.parseInt(clienteId);
        Cliente cliente = clienteRepository.findById(cliente_id).orElse(null);
        cliente.setSaldo(cliente.getSaldo()-valor);
        cliente = clienteRepository.save(cliente);

        return movimentacaoRepository.save(new Movimentacao(1, valor, horario,
                                                            cliente, null));
    }

    @PostMapping("/bancos/{bancoId}/agencias/{agenciaId}/clientes/{clienteId}/deposito")
    public Movimentacao deposit(@PathVariable String clienteId,
                                 @RequestBody Map<String, String> body) {
        double valor = Double.parseDouble(body.get("valor"));
        valor = (valor > 0) ? valor : -valor;
        Date horario = new Date(System.currentTimeMillis());

        int cliente_id = Integer.parseInt(clienteId);
        Cliente cliente = clienteRepository.findById(cliente_id).orElse(null);
        cliente.setSaldo(cliente.getSaldo()+valor);
        cliente = clienteRepository.save(cliente);

        return movimentacaoRepository.save(new Movimentacao(2, valor, horario,
                                                            cliente, null));
    }

    @PostMapping("/bancos/{bancoId}/agencias/{agenciaId}/clientes/{clienteId}/transferencia")
    public Movimentacao transfer(@PathVariable String clienteId,
                                @RequestBody Map<String, String> body) {
        double valor = Double.parseDouble(body.get("valor"));
        double valorTransferido = (valor < 0) ? valor : -valor;
        double valorRecebido = (valor > 0) ? valor : -valor;

        Date horario = new Date(System.currentTimeMillis());

        int cliente_id = Integer.parseInt(clienteId);
        Cliente clientePagador = clienteRepository.findById(cliente_id).orElse(null);
        clientePagador.setSaldo(clientePagador.getSaldo()-valor);
        clientePagador = clienteRepository.save(clientePagador);

        int destinatario = Integer.parseInt(body.get("destinatario"));
        Cliente clienteRecebedor = clienteRepository.findById(destinatario).orElse(null);
        clienteRecebedor.setSaldo(clienteRecebedor.getSaldo()+valor);
        clienteRecebedor = clienteRepository.save(clienteRecebedor);

        Movimentacao movimentacaoRecebedor = movimentacaoRepository
                .save(new Movimentacao(3, valorRecebido, horario,
                                       clienteRecebedor, clientePagador));

        return movimentacaoRepository.save(new Movimentacao(3, valorTransferido,
                horario, clientePagador, clienteRecebedor));
    }

}
