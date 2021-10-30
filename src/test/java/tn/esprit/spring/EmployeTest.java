package tn.esprit.spring;

import static org.junit.Assert.*;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.services.EmployeServiceImpl;
import tn.esprit.spring.services.EntrepriseServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class EmployeTest {
	
	@Autowired
	EmployeServiceImpl employeService;
	
	@Autowired
	EntrepriseServiceImpl entrepriseService;
	
	@Autowired
	DepartementRepository departementRepository;
	
	
	@Test
	public void testAjoutEmploye() {
		Employe employe = new Employe("Slama","ahmed khalil","Ahmedkhalil.slama@esprit.tn",true,Role.INGENIEUR);
		int id = employeService.ajouterEmploye(employe);
		Assert.assertNotNull(employeService.getEmployeById(id));
	}
	
	@Test 
	public void testMettreAjourEmailByEmployeId()
	{
		Employe employe = new Employe("Slama","ahmed khalil","Ahmedkhalil.slama@esprit.tn",true,Role.INGENIEUR);
		int id = employeService.ajouterEmploye(employe);
		employeService.mettreAjourEmailByEmployeId("olay@esprit.tn", id);
		Assert.assertTrue("not equal", employeService.getEmployeById(id).getEmail().equals("olay@esprit.tn"));
	}
	
	@Transactional
	@Test
	public void affecterEmployeADepartement()
	{
		Employe employe = new Employe("Slama","ahmed khalil","Ahmedkhalil.slama@esprit.tn",true,Role.INGENIEUR);
		int idEmploye = employeService.ajouterEmploye(employe);
		Departement departement = new Departement("Khalil's Departement");
		int idDepartement = entrepriseService.ajouterDepartement(departement);
		employeService.affecterEmployeADepartement(idEmploye, idDepartement);
		Assert.assertTrue(departementRepository.findById(idDepartement).get().getEmployes().indexOf(employe)!= -1);
	}
}
