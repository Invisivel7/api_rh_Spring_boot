package com.ApiRH.ApiRG.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ApiRH.ApiRG.models.Candidato;
import com.ApiRH.ApiRG.service.CandidatoService;

@Controller
@RequestMapping("/api")
public class CandidatoController {
	
	private CandidatoService candidatoService;
	
	public CandidatoController(CandidatoService candidatoService) {
		this.candidatoService = candidatoService;
	}
	
	@GetMapping("/candidatos")
	public List<Candidato> getAllCandidatos(){
		return candidatoService.getAllCandidatos();
	}
	
	@GetMapping("/candidatos/{id}")
	public ResponseEntity<?> getCandidatoById(@PathVariable Long id){
		Candidato candidato = candidatoService.getCandidatoId(id);
		if(candidato == null) ResponseEntity.notFound().build();
		return ResponseEntity.ok(candidato);
	}
	
	@PostMapping("/cadastroCandidato")
	public String postCandidato(@RequestBody Candidato candidato, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			attributes.addFlashAttribute("mensagem","Verifique os campos");
			return "redirect:/candidato";
		}
		candidatoService.postCandidato(candidato);
		attributes.addFlashAttribute("Candidato cadastrado com sucesso");
		return "redirect:/cadastroCandidato";
	}
	
	@PatchMapping("/candidatos/{id}")
	public ResponseEntity<?> updateCandidato(@PathVariable Long id, @RequestBody Candidato candidato){
		Candidato updateCandidato = candidatoService.updateCandidato(id, candidato);
		
		if(updateCandidato == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		return ResponseEntity.ok(updateCandidato);
	}
	
	public ResponseEntity<?> deleteCandidato(@PathVariable Long id){
		try {
			candidatoService.deleteCandidato(id);
			return new ResponseEntity<>("Candidato excluido com sucesso", HttpStatus.OK);
		} catch (Error e) {
			// TODO: handle exception
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

}
