package com.ApiRH.ApiRG.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ApiRH.ApiRG.models.Vaga;
import com.ApiRH.ApiRG.repository.VagaRepository;

@Service
public class VagaService {
	
	private VagaRepository vagaRepository;
	
	public VagaService(VagaRepository vagaRepository) {
		this.vagaRepository = vagaRepository;
	}
	
	public Vaga postVaga(Vaga vaga) {
		return vagaRepository.save(vaga);
	}
	
	public List<Vaga> getAllVagas(){
		return vagaRepository.findAll();
	}
	
	public void deleteVaga(Long id) {
		if(!vagaRepository.existsById(id)) {
			throw new Error("Vaga com este: " + id + "não encontrada/existe");
		}
		vagaRepository.deleteById(id);
	}
	
	public Vaga getVagaById(Long id) {
		return vagaRepository.findById(id).orElse(null);
	}
	
	public Vaga updateVaga(Long id, Vaga vaga) {
		Optional<Vaga> optionalVaga = vagaRepository.findById(id); 
		if(optionalVaga.isPresent()) {
			Vaga existingVaga = optionalVaga.get();
			
			existingVaga.setNome(vaga.getNome());
			existingVaga.setDescricao(vaga.getDescricao());
			existingVaga.setData(vaga.getData());
			existingVaga.setSalario(vaga.getSalario());
			
			return vagaRepository.save(existingVaga);
		}
		return null;
	}

}
