package tn.esprit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entities.Contrat;

@RestController
public class RestControlContrat {
@Autowired 
ContratService ContratService;

@GetMapping("/retrieve-all-contrat")
@ResponseBody
public List<Contrat> getContrat(){
	List<Contrat> contrat = ContratService.retrieveAllContrat();
	return contrat;
}

@GetMapping("/retrieve-contrat/{id}")
@ResponseBody
public Contrat getContratById(@PathVariable("id") int id){
	return ContratService.retrieveContrat(id);
}

@PostMapping("/add-contrat")
@ResponseBody
public String addContrat(@RequestBody Contrat u){
	Contrat contrat= ContratService.addContrat(u);
	return "this is contrat:"+contrat;
}

@DeleteMapping("/delete-contrat/{id}")
@ResponseBody
public void deleteContrat(@PathVariable("id") int id){
	ContratService.deleteContrat(id);
}

@PutMapping("/update-contrat")
@ResponseBody
public Contrat UpdateContrat(@RequestBody Contrat contrat){
	return ContratService.updateContrat(contrat);
}




}
