package com.example.BancoRestApi.banco;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
public class BancoController {

    @Autowired
    BancoRepository bancoRepository;

    @GetMapping("/bancos")
    public List<Banco> index() {
        return bancoRepository.findAll();
    }


    @GetMapping("/bancos/{id}")
    public Banco show(@PathVariable Integer id) {
        Banco banco = bancoRepository.findById(id).orElse(null);
        if(banco == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Banco não existe!");
        }

        return banco;
    }


    @PostMapping("/bancos")
    public Banco create(@RequestBody Map<String, String> body) {
        String nome = body.get("nome");
        return bancoRepository.save(new Banco(nome));
    }


    @PutMapping("/bancos/{id}")
    public Banco update(@PathVariable Integer id,
                        @RequestBody Map<String, String> body) {
        Banco banco = bancoRepository.findById(id).orElse(null);
        if(banco == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Banco não existe!");
        }

        banco.setNome(body.get("nome"));
        return bancoRepository.save(banco);
    }


    @DeleteMapping("bancos/{id}")
    public boolean delete(@PathVariable Integer id) {
        Banco banco = bancoRepository.findById(id).orElse(null);
        if(banco == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Banco não existe!");
        }

        bancoRepository.delete(banco);
        return true;
    }

}
