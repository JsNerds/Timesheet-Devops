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

import tn.esprit.spring.converter.DepartementConverter;
import tn.esprit.spring.converter.EntrepriseConverter;
import tn.esprit.spring.dto.DepartementDto;
import tn.esprit.spring.dto.EntrepriseDto;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.services.IEmployeService;
import tn.esprit.spring.services.IEntrepriseService;
import tn.esprit.spring.services.ITimesheetService;

@RestController
public class RestControlEntreprise {

	
	@Autowired
	IEmployeService iemployeservice;
	@Autowired
	IEntrepriseService ientrepriseservice;
	@Autowired
	ITimesheetService itimesheetservice;
	
	// Ajouter Enterprise : http://localhost:8081/SpringMVC/servlet/ajouterEntreprise

	@PostMapping("/ajouterEntreprise")
	@ResponseBody
	public int ajouterEntreprise(@RequestBody EntrepriseDto entrepriseDto) {
		ientrepriseservice.ajouterEntreprise(EntrepriseConverter.dtoToEntity(entrepriseDto));
		return entrepriseDto.getId();
	}
	
	// http://localhost:8081/SpringMVC/servlet/affecterDepartementAEntreprise/1/1
    @PutMapping(value = "/affecterDepartementAEntreprise/{iddept}/{identreprise}") 
	public void affecterDepartementAEntreprise(@PathVariable("iddept")int depId, @PathVariable("identreprise")int entrepriseId) {
		ientrepriseservice.affecterDepartementAEntreprise(depId, entrepriseId);
	}
    
    // http://localhost:8081/SpringMVC/servlet/deleteEntrepriseById/1
    @DeleteMapping("/deleteEntrepriseById/{identreprise}") 
	@ResponseBody 
	public void deleteEntrepriseById(@PathVariable("identreprise")int entrepriseId)
	{
		ientrepriseservice.deleteEntrepriseById(entrepriseId);
	}
    
    // http://localhost:8081/SpringMVC/servlet/getEntrepriseById/1
    @GetMapping(value = "getEntrepriseById/{identreprise}")
    @ResponseBody
	public Entreprise getEntrepriseById(@PathVariable("identreprise") int entrepriseId) {

		return ientrepriseservice.getEntrepriseById(entrepriseId);
	}
    
    // http://localhost:8081/SpringMVC/servlet/ajouterDepartement
 	@PostMapping("/ajouterDepartement")
 	@ResponseBody
	public int ajouterDepartement(@RequestBody DepartementDto dep) {
		return ientrepriseservice.ajouterDepartement(DepartementConverter.dtoToEntity(dep));
	}
	
 	 // http://localhost:8081/SpringMVC/servlet/getAllDepartementsNamesByEntreprise/1
    @GetMapping(value = "getAllDepartementsNamesByEntreprise/{identreprise}")
    @ResponseBody
	public List<String> getAllDepartementsNamesByEntreprise(@PathVariable("identreprise") int entrepriseId) {
		return ientrepriseservice.getAllDepartementsNamesByEntreprise(entrepriseId);
	}

    // URL : http://localhost:8081/SpringMVC/servlet/deleteDepartementById/3
    @DeleteMapping("/deleteDepartementById/{iddept}") 
	@ResponseBody 
	public void deleteDepartementById(@PathVariable("iddept") int depId) {
		ientrepriseservice.deleteDepartementById(depId);

	}
    
    
    @GetMapping(value = "getAllEntreprises")
    @ResponseBody
	public List<Entreprise> getAllntreprise() {
		return ientrepriseservice.retrieveAllEntreprises();
	}

    @GetMapping(value = "getAllDepartements")
    @ResponseBody
	public List<Departement> getAllDepartements() {
		return ientrepriseservice.retrieveAllDepartements();
	}

    
    @PutMapping("/updateDepartementId/{iddept}") 
	@ResponseBody 
	public Departement updateDepartement(@PathVariable("iddept") int depId, @RequestBody DepartementDto dep) {
		return ientrepriseservice.updateDepartement(depId, DepartementConverter.dtoToEntity(dep));
	}
    
    @PutMapping("/updateEntreprise/{idEntrep}") 
	@ResponseBody 
	public Entreprise updateEntreprise(@PathVariable("idEntrep") int idEntrep, @RequestBody EntrepriseDto entrepriseDto) {
		return ientrepriseservice.updateEntreprise(idEntrep, EntrepriseConverter.dtoToEntity(entrepriseDto));
	}
    
}
