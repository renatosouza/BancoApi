package com.example.BancoRestApi.agencia;

import com.example.BancoRestApi.banco.Banco;
import com.example.BancoRestApi.banco.BancoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
public class AgenciaController {

    @Autowired
    AgenciaRepository agenciaRepository;

    @Autowired
    BancoRepository bancoRepository;

    @GetMapping("/bancos/{bancoId}/agencias")
    public List<Agencia> index(@PathVariable Integer bancoId) {
        Banco banco = bancoRepository.findById(bancoId).orElse(null);
        if(banco == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Banco não existe!");
        }
        return agenciaRepository.findByBanco_id(bancoId);
    }


    @GetMapping("/bancos/{bancoId}/agencias/{id}")
    public Agencia show(@PathVariable Integer bancoId,
                        @PathVariable Integer id) {
        Banco banco = bancoRepository.findById(bancoId).orElse(null);
        if(banco == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Banco não existe!");
        }

        Agencia agencia = agenciaRepository.findById(id).orElse(null);
        if(agencia == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Agência não existe!");
        }
        return agencia;
    }


    @PostMapping("/bancos/{bancoId}/agencias")
    public Agencia create(@PathVariable Integer bancoId,
                          @RequestBody Map<String, String> body) {
        Banco banco = bancoRepository.findById(bancoId).orElse(null);
        if(banco == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Banco não existe!");
        }

        String codigo = body.get("codigo");
        String endereco = body.get("endereco");
        String telefone = body.get("telefone");
        Agencia agencia = new Agencia(codigo, endereco, telefone, banco);
        return agenciaRepository.save(agencia);
    }


    @PutMapping("/bancos/{bancoId}/agencias/{id}")
    public Agencia update(@PathVariable Integer bancoId,
                          @PathVariable Integer id,
                          @RequestBody Map<String, String> body) {
        Banco banco = bancoRepository.findById(bancoId).orElse(null);
        if(banco == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Banco não existe!");
        }

        Agencia agencia = agenciaRepository.findById(id).orElse(null);
        if(agencia == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Agência não existe!");
        }

        agencia.setCodigo(body.get("codigo"));
        agencia.setEndereco(body.get("endereco"));
        agencia.setTelefone(body.get("telefone"));
        return agenciaRepository.save(agencia);
    }


    @DeleteMapping("/bancos/{bancoId}/agencias/{id}")
    public boolean delete(@PathVariable Integer bancoId,
                          @PathVariable Integer id) {
        Banco banco = bancoRepository.findById(bancoId).orElse(null);
        if(banco == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Banco não existe!");
        }

        Agencia agencia = agenciaRepository.findById(id).orElse(null);
        if(agencia == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Agência não existe!");
        }

        agenciaRepository.delete(agencia);
        return true;
    }

}
