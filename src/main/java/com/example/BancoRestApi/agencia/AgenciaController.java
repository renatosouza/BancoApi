package com.example.BancoRestApi.agencia;

import com.example.BancoRestApi.banco.Banco;
import com.example.BancoRestApi.banco.BancoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class AgenciaController {

    @Autowired
    AgenciaRepository agenciaRepository;

    @Autowired
    BancoRepository bancoRepository;

    @GetMapping("/bancos/{bancoId}/agencias")
    public List<Agencia> index(@PathVariable String bancoId) {
        int banco_id = Integer.parseInt(bancoId);
        Banco banco = bancoRepository.findById(banco_id).orElse(null);
        return banco.getAgencias();
    }

    @GetMapping("/bancos/{bancoId}/agencias/{id}")
    public Agencia show(@PathVariable String id) {
        int agenciaId = Integer.parseInt(id);
        return agenciaRepository.findById(agenciaId).orElse(null);
    }

    @PostMapping("/bancos/{bancoId}/agencias")
    public Agencia create(@PathVariable String bancoId,
                          @RequestBody Map<String, String> body) {
        String codigo = body.get("codigo");
        String endereco = body.get("endereco");
        String telefone = body.get("telefone");
        int banco_id = Integer.parseInt(bancoId);
        Banco banco = bancoRepository.findById(banco_id).orElse(null);
        Agencia agencia = new Agencia(codigo, endereco, telefone, banco);
        System.out.println(agencia);
        Agencia saved = agenciaRepository.save(agencia);
        System.out.println(saved);
        return saved;
    }

    @PutMapping("/bancos/{bancoId}/agencias/{id}")
    public Agencia update(@PathVariable String id,
                          @RequestBody Map<String, String> body) {
        int agenciaId = Integer.parseInt(id);
        Agencia agencia = agenciaRepository.findById(agenciaId).orElse(null);
        agencia.setCodigo(body.get("codigo"));
        agencia.setEndereco(body.get("endereco"));
        agencia.setTelefone(body.get("telefone"));
        return agenciaRepository.save(agencia);
    }

    @DeleteMapping("/bancos/{bancoId}/agencias/{id}")
    public boolean delete(@PathVariable String id) {
        int agenciaId = Integer.parseInt(id);
        agenciaRepository.deleteById(agenciaId);
        return true;
    }

}
