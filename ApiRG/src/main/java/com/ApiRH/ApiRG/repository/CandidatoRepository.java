package com.ApiRH.ApiRG.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ApiRH.ApiRG.models.Candidato;

@Repository
public interface CandidatoRepository extends JpaRepository<Candidato, Long> {

}
