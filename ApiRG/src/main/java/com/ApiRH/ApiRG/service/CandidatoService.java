package com.ApiRH.ApiRG.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ApiRH.ApiRG.models.Candidato;
import com.ApiRH.ApiRG.repository.CandidatoRepository;

@Service
public class CandidatoService {
	
	private CandidatoRepository candidatoRepository;
	
	public CandidatoService(CandidatoRepository candidatoRepository) {
		this.candidatoRepository = candidatoRepository;
	}
	
	public Candidato postCandidato(Candidato candidato) {
		return candidatoRepository.save(candidato);
	}
	
	public List<Candidato> getAllCandidatos(){
		return candidatoRepository.findAll();
	}
	
	public Candidato getCandidatoId(Long id) {
		return candidatoRepository.findById(id).orElse(null);
	}
	
	public void deleteCandidato(Long id) {
		if(!candidatoRepository.existsById(id)) {
			throw new Error("Candidato com o ID: " + id + " não encontrado");
		}
		candidatoRepository.deleteById(id);
	}
	
	public Candidato updateCandidato(Long id, Candidato candidato) {
		Optional<Candidato> optionalCandidato = candidatoRepository.findById(id);
		if(optionalCandidato.isPresent()) {
			Candidato existingCandidato = optionalCandidato.get();
			
			existingCandidato.setName(candidato.getName());
			existingCandidato.setEmail(candidato.getEmail());
			
			return candidatoRepository.save(existingCandidato);
		}
		return null;
	}

}
