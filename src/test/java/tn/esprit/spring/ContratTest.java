package tn.esprit.spring;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.controller.ContratService;
import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.services.EmployeServiceImpl;
import tn.esprit.spring.services.EntrepriseServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class ContratTest {
	@Autowired
	ContratService ContratService;
	@Autowired
	EmployeServiceImpl employeService;
	@Test
	@Order(1)
	public void testajoutcontrat() {
		Employe employe = new Employe("sahnoun","aziz","mohamedaziz.sahnoun@esprit.tn",true,Role.INGENIEUR);
		int id = employeService.ajouterEmploye(employe);
		Date date = new Date(System.currentTimeMillis());
		Contrat contrat = new Contrat(5, date, "cdi", 800,employe);
		int referenceContrat = ContratService.addContrat(contrat).getReference();
		Assert.assertNotNull(ContratService.getContratById(referenceContrat));
	}
	
	
	@Test
	@Order(2)
	public void testdeletecontrat() {
		Employe employe = new Employe("sahnoun","aziz","mohamedaziz.sahnoun@esprit.tn",true,Role.INGENIEUR);
		int id = employeService.ajouterEmploye(employe);
		Date date = new Date(System.currentTimeMillis());
		Contrat contrat = new Contrat(5, date, "cdi", 800,employe);
		int idcontrat = ContratService.addContrat(contrat).getReference();
		
		int value = ContratService.deleteContrat(idcontrat);
		Assert.assertEquals(1, value);
		
		int WrongValue =  ContratService.deleteContrat(idcontrat);
		Assert.assertEquals(WrongValue, -1);
	}
	
	@Test
	@Order(3)
	public void testupdatecontrat() {
		Employe employe = new Employe("sahnoun","aziz","mohamedaziz.sahnoun@esprit.tn",true,Role.INGENIEUR);
		int id = employeService.ajouterEmploye(employe);
		Date date = new Date(System.currentTimeMillis());
		Contrat contrat = new Contrat(5, date, "cdi", 800,employe);
		int idcontrat = ContratService.addContrat(contrat).getReference();
		Contrat contrat2 = new Contrat(5, date, "cdd", 900,employe);
		Assert.assertNotNull(ContratService.updateContrat(contrat2));
	}
	@Test
	@Order(4)
	public void testgetcontrats() {
		Date date = new Date(System.currentTimeMillis());
		
		Employe employe = new Employe("sahnoun","aziz","mohamedaziz.sahnoun@esprit.tn",true,Role.INGENIEUR);
		int idemp = employeService.ajouterEmploye(employe);
		Contrat contrat = new Contrat(5, date, "cdi", 800,employe);
		
		Employe employe2 = new Employe("Saidi","ahmed","Ahmed.Saidi@esprit.tn",true,Role.INGENIEUR);
		int idemp2 = employeService.ajouterEmploye(employe2);
		Contrat contrat2 = new Contrat(5, date, "cdd", 900,employe2);
		
		int referenceContrat1 = ContratService.addContrat(contrat).getReference();
		int referenceContrat2 = ContratService.addContrat(contrat2).getReference();
		
		
		Assert.assertNotNull(ContratService.retrieveAllContrat());
	}
	
	@Test
	@Order(5)
	public void testgetcontrat_byid() {
		Date date = new Date(System.currentTimeMillis());
		
		Employe employe = new Employe("sahnoun","aziz","mohamedaziz.sahnoun@esprit.tn",true,Role.INGENIEUR);
		int idemp = employeService.ajouterEmploye(employe);
		Contrat contrat = new Contrat(5, date, "cdi", 22,employe);
		
		Employe employe2 = new Employe("Saidi","ahmed","Ahmed.Saidi@esprit.tn",true,Role.INGENIEUR);
		int idemp2 = employeService.ajouterEmploye(employe2);
		Contrat contrat2 = new Contrat(5, date, "cdd", 23,employe2);
		
		int id1 = ContratService.addContrat(contrat).getReference();
		int id2= ContratService.addContrat(contrat2).getReference();
		
		Assert.assertNotNull(ContratService.getContratById(id1));
	}
	

}
