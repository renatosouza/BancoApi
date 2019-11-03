package com.example.BancoRestApi.cliente;

import com.example.BancoRestApi.agencia.Agencia;
import com.example.BancoRestApi.agencia.AgenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ClienteController {

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    AgenciaRepository agenciaRepository;

    @GetMapping("/bancos/{bancoId}/agencias/{agenciaId}/clientes")
    public List<Cliente> index(@PathVariable String agenciaId) {
        int agencia_id = Integer.parseInt(agenciaId);
        Agencia agencia = agenciaRepository.findById(agencia_id).orElse(null);
        return agencia.getClientes();
    }

    @GetMapping("/bancos/{bancoId}/agencias/{agenciaId}/clientes/{id}")
    public Cliente show(@PathVariable String id) {
        int clienteId = Integer.parseInt(id);
        return clienteRepository.findById(clienteId).orElse(null);
    }

    @PostMapping("/bancos/{bancoId}/agencias/{agenciaId}/clientes")
    public Cliente create(@PathVariable String agenciaId,
                          @RequestBody Map<String, String> body) {
        String conta = body.get("conta");
        String nome = body.get("nome");
        String endereco = body.get("endereco");
        String telefone = body.get("telefone");
        String email = body.get("email");
        double saldo = Double.parseDouble(body.get("saldo"));
        int agencia_id = Integer.parseInt(agenciaId);
        Agencia agencia = agenciaRepository.findById(agencia_id).orElse(null);
        return clienteRepository.save(new Cliente(conta, nome, endereco, telefone,
                                                  email, saldo, agencia));
    }

    @PutMapping("/bancos/{bancoId}/agencias/{agenciaId}/clientes/{id}")
    public Cliente update(@PathVariable String id,
                          @RequestBody Map<String, String> body) {
        int clienteId = Integer.parseInt(id);
        Cliente cliente = clienteRepository.findById(clienteId).orElse(null);
        cliente.setConta(body.get("conta"));
        cliente.setNome(body.get("nome"));
        cliente.setEndereco(body.get("endereco"));
        cliente.setTelefone(body.get("telefone"));
        cliente.setEmail(body.get("email"));
        return clienteRepository.save(cliente);
    }

    @DeleteMapping("/bancos/{bancoId}/agencias/{agenciaId}/clientes/{id}")
    public boolean delete(@PathVariable String id) {
        int clienteId = Integer.parseInt(id);
        clienteRepository.deleteById(clienteId);
        return true;
    }

}
