package com.ApiRH.ApiRG.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ApiRH.ApiRG.models.Vaga;
import com.ApiRH.ApiRG.service.VagaService;

@Controller
@RequestMapping("/api")
public class VagaController {
	
	private VagaService vagaService;
	//private CandidatoService candidatoService;
	
	@GetMapping("/vagas")
	public List<Vaga> getAllVagas(){
		return vagaService.getAllVagas();
	}
	
	@GetMapping("/vagas/{id}")
	public ResponseEntity<?> getVagaById(@PathVariable Long id){
		Vaga vaga = vagaService.getVagaById(id);
		if(vaga == null)return ResponseEntity.notFound().build();
		return ResponseEntity.ok(vaga);
	}
	
	@RequestMapping(value = "/cadastroVaga", method = RequestMethod.GET)
	public String form() {
		return "vaga/vagaVews";
	}
	
	@RequestMapping(value = "/cadastroVaga", method = RequestMethod.POST)
	public String form(@RequestBody Vaga vaga, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			attributes.addFlashAttribute("mensagem","Verifique os campos");
			return "redirect:/vagaVews";
		}
		vagaService.postVaga(vaga);
		attributes.addFlashAttribute("Vaga cadastrada com sucess");
		return "redirect:/vagaVews";
	}
	
	@PatchMapping("/vagas/{id}")
	public ResponseEntity<?> updateVaga(@PathVariable Long id, @RequestBody Vaga vaga){
		Vaga updateVaga = vagaService.updateVaga(id, vaga);
		
		if(updateVaga == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		return ResponseEntity.ok(updateVaga);
	}
	
	@DeleteMapping("/vagas/{id}")
	public ResponseEntity<?> deleteVaga(Long id){
		try {
			vagaService.deleteVaga(id);
			return new ResponseEntity<>("Vaga com o id: " + id + "deletada com sucesso", HttpStatus.OK);
		} catch (Error e) {
			// TODO: handle exception
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

}
