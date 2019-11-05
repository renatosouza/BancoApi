package com.example.BancoRestApi.cliente;

import com.example.BancoRestApi.agencia.Agencia;
import com.example.BancoRestApi.agencia.AgenciaRepository;
import com.example.BancoRestApi.banco.Banco;
import com.example.BancoRestApi.banco.BancoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
public class ClienteController {

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    AgenciaRepository agenciaRepository;

    @Autowired
    BancoRepository bancoRepository;

    @GetMapping("/bancos/{bancoId}/agencias/{agenciaId}/clientes")
    public List<Cliente> index(@PathVariable Integer bancoId,
                               @PathVariable Integer agenciaId) {
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

        return clienteRepository.findByAgencia_id(agenciaId);
    }


    @GetMapping("/bancos/{bancoId}/agencias/{agenciaId}/clientes/{id}")
    public Cliente show(@PathVariable Integer bancoId,
                        @PathVariable Integer agenciaId,
                        @PathVariable Integer id) {
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

        Cliente cliente = clienteRepository.findById(id).orElse(null);
        if(cliente == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Cliente não existe!");
        }

        return cliente;
    }


    @PostMapping("/bancos/{bancoId}/agencias/{agenciaId}/clientes")
    public Cliente create(@PathVariable Integer bancoId,
                          @PathVariable Integer agenciaId,
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

        String conta = body.get("conta");
        String nome = body.get("nome");
        String endereco = body.get("endereco");
        String telefone = body.get("telefone");
        String email = body.get("email");
        double saldo = Double.parseDouble(body.get("saldo"));
        Cliente cliente = new Cliente(conta, nome, endereco, telefone,
                                      email, saldo, agencia);
        return clienteRepository.save(cliente);
    }


    @PutMapping("/bancos/{bancoId}/agencias/{agenciaId}/clientes/{id}")
    public Cliente update(@PathVariable Integer bancoId,
                          @PathVariable Integer agenciaId,
                          @PathVariable Integer id,
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

        Cliente cliente = clienteRepository.findById(id).orElse(null);
        if(cliente == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Cliente não existe!");
        }

        cliente.setConta(body.get("conta"));
        cliente.setNome(body.get("nome"));
        cliente.setEndereco(body.get("endereco"));
        cliente.setTelefone(body.get("telefone"));
        cliente.setEmail(body.get("email"));
        return clienteRepository.save(cliente);
    }


    @DeleteMapping("/bancos/{bancoId}/agencias/{agenciaId}/clientes/{id}")
    public boolean delete(@PathVariable Integer bancoId,
                          @PathVariable Integer agenciaId,
                          @PathVariable Integer id) {
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

        Cliente cliente = clienteRepository.findById(id).orElse(null);
        if(cliente == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Cliente não existe!");
        }

        clienteRepository.delete(cliente);
        return true;
    }

}
