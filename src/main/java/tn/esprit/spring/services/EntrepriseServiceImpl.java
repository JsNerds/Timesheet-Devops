package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EntrepriseRepository;

@Service
public class EntrepriseServiceImpl implements IEntrepriseService {
	public static final Logger logger = Logger.getLogger(EntrepriseServiceImpl.class);

	@Autowired
    EntrepriseRepository entrepriseRepoistory;
	@Autowired
	DepartementRepository deptRepoistory;
	
	public int ajouterEntreprise(Entreprise entreprise) {
		try {
			logger.info("START ajouterEntreprise ");
			logger.debug("debut d'ajout de l'entreprise: " + entreprise.getName());
			entrepriseRepoistory.save(entreprise);
			logger.info("out of ajouterEntreprise()");
			logger.debug("l'entreprise: " + entreprise.getName() + " de l'id: " + entreprise.getId() + " ajoutée avec succé");

		}catch(Exception e){
			logger.error("Erreur: "+e);
		}
		logger.info("END ajouterEntreprise ");

		return entreprise.getId();
	}
	public int ajouterDepartement(Departement dep) {
		try {
			logger.info("START ajouterEntreprise ");
			logger.debug("debut d'ajout de l'entreprise: " + dep.getName());
			deptRepoistory.save(dep);
			logger.info("out of ajouterdep()");
			logger.debug("l'dep: " + dep.getName() + " de l'id: " + dep.getId() + " ajoutée avec succé");

		}catch(Exception e){
			logger.error("Erreur: "+e);
		}
		logger.info("END ajouterdep ");
	
		return dep.getId();
	}
	
	public void affecterDepartementAEntreprise(int depId, int entrepriseId) {
		//Le bout Master de cette relation N:1 est departement  
				//donc il faut rajouter l'entreprise a departement 
				// ==> c'est l'objet departement(le master) qui va mettre a jour l'association
				//Rappel : la classe qui contient mappedBy represente le bout Slave
				//Rappel : Dans une relation oneToMany le mappedBy doit etre du cote one.
				Entreprise entrepriseManagedEntity = entrepriseRepoistory.findById(entrepriseId).get();
				Departement depManagedEntity = deptRepoistory.findById(depId).get();
				
				depManagedEntity.setEntreprise(entrepriseManagedEntity);
				deptRepoistory.save(depManagedEntity);
		
	}
	
	public List<String> getAllDepartementsNamesByEntreprise(int entrepriseId) {
		Entreprise entrepriseManagedEntity = entrepriseRepoistory.findById(entrepriseId).get();
		List<String> depNames = new ArrayList<>();
		for(Departement dep : entrepriseManagedEntity.getDepartements()){
			depNames.add(dep.getName());
		}
		
		return depNames;
	}

	@Transactional
	public void deleteEntrepriseById(int entrepriseId) {
		if(entrepriseRepoistory.findById(entrepriseId).isPresent()) {
			entrepriseRepoistory.delete(entrepriseRepoistory.findById(entrepriseId).get());	

		}
	}

	@Transactional
	public void deleteDepartementById(int depId) {
		deptRepoistory.delete(deptRepoistory.findById(depId).get());	
	}


	public Entreprise getEntrepriseById(int entrepriseId) {
		if(entrepriseRepoistory.findById(entrepriseId).isPresent()) {
			return entrepriseRepoistory.findById(entrepriseId).get();	

		}
		else 
			return null;
	}

}
