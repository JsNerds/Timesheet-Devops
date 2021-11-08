package tn.esprit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.converter.ContratConverter;
import tn.esprit.spring.dto.ContratDto;
import tn.esprit.spring.entities.Contrat;

@RestController
public class RestControlContrat {
@Autowired 
ContratService contratService;

@GetMapping("/retrieve-all-contrat")
@ResponseBody
public List<Contrat> getContrat(){
	return contratService.retrieveAllContrat();
}

@GetMapping("/retrieve-contrat/{id}")
@ResponseBody
public Contrat getContratById(@PathVariable("id") String id){
	return contratService.retrieveContrat(id);
}

@PostMapping("/add-contrat")
@ResponseBody
public String addContrat(@RequestBody ContratDto u){
	Contrat contrat= contratService.addContrat(ContratConverter.dtoToEntity(u));
	return "this is contrat:"+contrat;
}

@DeleteMapping("/delete-contrat/{id}")
@ResponseBody
public void deleteContrat(@PathVariable("id") String id){
	contratService.deleteContrat(id);
}

@PutMapping("/update-contrat")
@ResponseBody
public Contrat updateContrat(@RequestBody  ContratDto u){
	return contratService.updateContrat(ContratConverter.dtoToEntity(u));
}




}