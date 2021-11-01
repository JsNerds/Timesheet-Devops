package tn.esprit.spring;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.services.IEntrepriseService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EntrepriseTest {

	@Autowired
	private IEntrepriseService serviceEntreprise;


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

		int idEntrepDep=serviceEntreprise.affecterDepartementAEntreprise(idDep, idEntrep);
		Assert.assertEquals(idEntrepDep,idEntrep);

	}

	@Test
	public void testdeleteEntrepriseById() {
		
		Entreprise ent = new Entreprise("Soc", "DEV");
		int id = serviceEntreprise.ajouterEntreprise(ent);
		
		int value = serviceEntreprise.deleteEntrepriseById(id);
		Assert.assertEquals(1, value);
		
		int WrongValue = serviceEntreprise.deleteEntrepriseById(1812132);
		Assert.assertEquals(WrongValue, -1);

	}

	@Test
	public void testdeleteDepartementById() {
		Departement dep = new Departement("Info");
		int id = serviceEntreprise.ajouterDepartement(dep);
		
		int value = serviceEntreprise.deleteDepartementById(id);
		Assert.assertEquals(1, value);
		
		int WrongValue = serviceEntreprise.deleteDepartementById(1812132);
		Assert.assertEquals(WrongValue, -1);

	}

	@Test
	public void testgetEntrepriseById() {

		Entreprise ent = new Entreprise("Soc", "DEV");
		int id = serviceEntreprise.ajouterEntreprise(ent);
		
		Entreprise e1 = serviceEntreprise.getEntrepriseById(id);
		Assert.assertNotNull(e1);

		Entreprise e2 = serviceEntreprise.getEntrepriseById(213232);
		Assert.assertNull(e2);
	};

	@Test
	public void testgetDepartementById() {
		Departement dep = new Departement("Info");
		int id = serviceEntreprise.ajouterDepartement(dep);
		
		
		Departement d1 = serviceEntreprise.getDepartementById(id);
		Assert.assertNotNull(d1);

		Departement d2 = serviceEntreprise.getDepartementById(213232);
		Assert.assertNull(d2);
	};

}
