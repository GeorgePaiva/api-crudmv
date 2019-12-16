package com.george.apitestemv.controller;

import com.george.apitestemv.model.Profissional;
import com.george.apitestemv.repository.ProfissionalRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping({"/profissionais"})
public class ProfissionalController {

    private ProfissionalRepository repository;

    ProfissionalController(ProfissionalRepository contactRepository) {
        this.repository = contactRepository;
    }

    @GetMapping
    public List<Profissional> findAll() {
        return repository.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<Profissional> findById(@PathVariable Long id) {
        return repository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Profissional create(@RequestBody Profissional profissional) {
        return repository.save(profissional);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Profissional> update(@PathVariable("id") Long id,
                                               @RequestBody Profissional profissional) {
        return repository.findById(id)
                .map(record -> {
                    record.setNome(profissional.getNome());
                    record.setEndereco(profissional.getEndereco());
                    record.setTelefones(profissional.getTelefones());
                    Profissional updated = repository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path ={"/{id}"})
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return repository.findById(id)
                .map(record -> {
                    repository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
