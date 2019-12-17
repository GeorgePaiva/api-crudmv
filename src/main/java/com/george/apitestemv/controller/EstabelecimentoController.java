package com.george.apitestemv.controller;

import com.george.apitestemv.model.Estabelecimento;
import com.george.apitestemv.repository.EstabelecimentoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping({"/estabelecimentos"})
public class EstabelecimentoController {

    private EstabelecimentoRepository repository;

    EstabelecimentoController(EstabelecimentoRepository estabelecimentoRepository) {
        this.repository = estabelecimentoRepository;
    }

    @GetMapping
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Estabelecimento> findAll() {
        return repository.findAll();
    }

    @GetMapping(path = {"/{id}"})
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Estabelecimento> findById(@PathVariable Long id) {
        return repository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Estabelecimento create(@RequestBody Estabelecimento estabelecimento) {
        return repository.save(estabelecimento);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Estabelecimento> update(@PathVariable("id") Long id,
                                                  @RequestBody Estabelecimento estabelecimento) {
        return repository.findById(id)
                .map(record -> {
                    record.setNome(estabelecimento.getNome());
                    record.setEndereco(estabelecimento.getEndereco());
                    record.setTelefones(estabelecimento.getTelefones());
                    Estabelecimento updated = repository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return repository.findById(id)
                .map(record -> {
                    repository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
