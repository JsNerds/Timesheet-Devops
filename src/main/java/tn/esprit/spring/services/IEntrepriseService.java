package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;

public interface IEntrepriseService {

	public int ajouterEntreprise(Entreprise entreprise);

	public int ajouterDepartement(Departement dep);

	public int affecterDepartementAEntreprise(int depId, int entrepriseId);

	List<String> getAllDepartementsNamesByEntreprise(int entrepriseId);

	public int deleteEntrepriseById(int entrepriseId);

	public int deleteDepartementById(int depId);

	public Entreprise getEntrepriseById(int entrepriseId);

	public Departement getDepartementById(int departementId);
	
	public List<Entreprise> retrieveAllEntreprises();
	
	public List<Departement> retrieveAllDepartements(); 
	
	public int deleteAllEntreprises();
	
	public int deleteAllDepartements(); 
	
	public Entreprise updateEntreprise(int entrepId , Entreprise newEntrep);

	public Departement updateDepartement(int depId, Departement newDep);
	

}
