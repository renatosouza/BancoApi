package com.example.BancoRestApi.banco;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Banco show(@PathVariable String id) {
        int bancoId = Integer.parseInt(id);
        return bancoRepository.findById(bancoId).orElse(null);
    }

    @PostMapping("/bancos")
    public Banco create(@RequestBody Map<String, String> body) {
        String nome = body.get("nome");
        return bancoRepository.save(new Banco(nome));
    }

    @PutMapping("/bancos/{id}")
    public Banco update(@PathVariable String id,
                        @RequestBody Map<String, String> body) {
        int bancoId = Integer.parseInt(id);
        Banco banco = bancoRepository.findById(bancoId).orElse(null);
        banco.setNome(body.get("nome"));
        return bancoRepository.save(banco);
    }

    @DeleteMapping("bancos/{id}")
    public boolean delete(@PathVariable String id) {
        int bancoId = Integer.parseInt(id);
        bancoRepository.deleteById(bancoId);
        return true;
    }
}
