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
		logger.info("START ajouterEntreprise ");

		try {
			logger.debug(entreprise.getId());

			logger.trace("debut d'ajout de l'entreprise: " + entreprise.getName());
			entrepriseRepoistory.save(entreprise);
			logger.trace("fin ajout");

			logger.debug("l'entreprise: " + entreprise.getName() + " de l'id: " + entreprise.getId()
					+ " ajoutée avec succé");

		} catch (Exception e) {
			logger.error("Erreur:" + e);
		}
		logger.info("END ajouterEntreprise ");

		return entreprise.getId();
	}

	public int ajouterDepartement(Departement dep) {
		logger.info("START ajouterEntreprise ");

		try {
			logger.debug(dep.getId());

			logger.trace("debut d'ajout du departement: " + dep.getName());
			deptRepoistory.save(dep);
			logger.trace("fin Ajout");

			logger.debug("l'dep: " + dep.getName() + " de l'id: " + dep.getId() + " ajoutée avec succé");

		} catch (Exception e) {
			logger.error("Erreur:" + e);
		}
		logger.info("END ajouterdep ");

		return dep.getId();
	}

	public int affecterDepartementAEntreprise(int depId, int entrepriseId) {
		logger.info("START affecterDepartementAEntreprise ");
		Departement dep = new Departement();
		try {
			logger.debug("init dep" + dep);

			logger.trace("début: récuperer entrep by ID");
			Entreprise entrepriseManagedEntity = entrepriseRepoistory.findById(entrepriseId).get();
			logger.trace("fin: récuperer entrep by ID");

			logger.trace("début: récuperer dep by ID");
			Departement depManagedEntity = deptRepoistory.findById(depId).get();
			logger.trace("début: récuperer dep by ID");

			logger.trace("début: affectation d'entrep -> departement");
			depManagedEntity.setEntreprise(entrepriseManagedEntity);
			deptRepoistory.save(depManagedEntity);
			logger.trace("fin: affectation d'entrep -> departement");

			dep = depManagedEntity;

			logger.debug("new Dep" + dep);

			logger.debug("Entrep: " + depManagedEntity.getEntreprise().getId() + "-"
					+ depManagedEntity.getEntreprise().getName() + "affecté au department:" + depManagedEntity.getName()
					+ "-" + depManagedEntity.getId());

		} catch (Exception e) {
			logger.error("Erreur: " + e);

		}

		logger.info("END affecterDepartementAEntreprise ");
		return dep.getEntreprise().getId();
	}

	public List<String> getAllDepartementsNamesByEntreprise(int entrepriseId) {
		Entreprise entrepriseManagedEntity = entrepriseRepoistory.findById(entrepriseId).get();
		List<String> depNames = new ArrayList<>();
		for (Departement dep : entrepriseManagedEntity.getDepartements()) {
			depNames.add(dep.getName());
		}

		return depNames;
	}

	@Transactional
	public int deleteEntrepriseById(int entrepriseId) {
		logger.info("START deleteEntrepriseById ");
		try {
			logger.trace("Début Test : verifier l'exstence du l'entrep");
			if (entrepriseRepoistory.findById(entrepriseId).isPresent()) {
				logger.debug("Entrep exitse:" + entrepriseRepoistory.findById(entrepriseId).get().getId());

				logger.trace("débbut suppression");
				entrepriseRepoistory.delete(entrepriseRepoistory.findById(entrepriseId).get());
				logger.trace("fin suppression");
				logger.trace("FIN Test : verifier l'exstence du l'entrep");
				return 1;
			} else {

				logger.trace("Entrep n'exitse pas");
				logger.trace("FIN Test : verifier l'exstence du l'entrep");
				return -1;
			}

		} catch (Exception e) {
			logger.error("Erreur: " + e);

		}

		logger.debug("Entrep suprimée:" + entrepriseRepoistory.findById(entrepriseId).get().getId());
		logger.info("END deleteEntrepriseById ");

		return 0;

	}

	@Transactional
	public void deleteDepartementById(int depId) {
		deptRepoistory.delete(deptRepoistory.findById(depId).get());
	}

	public Entreprise getEntrepriseById(int entrepriseId) {
		if (entrepriseRepoistory.findById(entrepriseId).isPresent()) {
			return entrepriseRepoistory.findById(entrepriseId).get();

		} else
			return null;
	}

	public Departement getDepartementById(int departementId) {
		if (deptRepoistory.findById(departementId).isPresent()) {
			return deptRepoistory.findById(departementId).get();
		} else
			return null;
	}

}
