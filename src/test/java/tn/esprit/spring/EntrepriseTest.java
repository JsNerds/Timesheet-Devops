package tn.esprit.spring;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.EntrepriseRepository;
import tn.esprit.spring.services.IEntrepriseService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EntrepriseTest {

	@Autowired
	private IEntrepriseService serviceEntreprise;

	@Autowired
	private EntrepriseRepository repoEntreprise;

	@Test
	public void testAjouterEntreprise() {
		Entreprise ent = new Entreprise("SaidiJam Mat", "Agriculture");
		int id = serviceEntreprise.ajouterEntreprise(ent);
		Assert.assertNotNull(serviceEntreprise.getEntrepriseById(id));
	}

	@Test
	public void testAjouterDepartment() {
		Departement dep = new Departement("Agriculture");
		int id = serviceEntreprise.ajouterDepartement(dep);
		Assert.assertNotNull(serviceEntreprise.getDepartementById(id));
	}

	@Test
	public void testaffecterDepartementAEntreprise() {
		Entreprise ent = new Entreprise("Espritt", "Education");
		int idEntrep = serviceEntreprise.ajouterEntreprise(ent);

		Departement dep = new Departement("Web");
		int idDep = serviceEntreprise.ajouterDepartement(dep);

		Assert.assertNotNull(serviceEntreprise.affecterDepartementAEntreprise(idDep, idEntrep));

	}

}
