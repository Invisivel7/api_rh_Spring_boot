package com.ApiRH.ApiRG.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ApiRH.ApiRG.models.Vaga;

@Repository
public interface VagaRepository extends JpaRepository<Vaga, Long> {

}
