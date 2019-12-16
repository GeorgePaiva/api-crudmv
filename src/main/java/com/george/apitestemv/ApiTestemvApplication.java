package com.george.apitestemv;

import com.george.apitestemv.model.Estabelecimento;
import com.george.apitestemv.model.Profissional;
import com.george.apitestemv.repository.EstabelecimentoRepository;
import com.george.apitestemv.repository.ProfissionalRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.LongStream;

@SpringBootApplication
public class ApiTestemvApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiTestemvApplication.class, args);
    }

    @Bean
    CommandLineRunner init(ProfissionalRepository repository) {
        return args -> {
            repository.deleteAll();
            LongStream.range(1, 11)
                    .mapToObj(i -> {
                        Profissional p = new Profissional();
                        p.setNome("Profissional " + i);
                        p.setEndereco("profissional" + i + "@email.com");
                        p.setTelefones("(111) 111-1111");
                        return p;
                    })
                    .map(v -> repository.save(v))
                    .forEach(System.out::println);
        };
    }

    @Bean
    CommandLineRunner init1(EstabelecimentoRepository repository) {
        return args -> {
            repository.deleteAll();
            LongStream.range(1, 11)
                    .mapToObj(i -> {
                        Estabelecimento e = new Estabelecimento();
                        e.setNome("Estabelecimento " + i);
                        e.setEndereco("Rua A" + i + " Centro");
                        e.setTelefones("(111) 111-1111");
                        return e;
                    })
                    .map(v -> repository.save(v))
                    .forEach(System.out::println);
        };
    }
}

