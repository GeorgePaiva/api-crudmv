package com.george.apitestemv.repository;

import com.george.apitestemv.model.Estabelecimento;
import com.george.apitestemv.model.Profissional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstabelecimentoRepository extends JpaRepository<Estabelecimento, Long> {
}
