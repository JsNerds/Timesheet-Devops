package tn.esprit.spring;

import static org.junit.Assert.*;

import java.util.Date;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.services.EmployeServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class ContratTest {
	@Autowired
	EmployeServiceImpl employeService;
	
	@Test
	public void testajoutercontrat()
	{
		Date date = new Date(System.currentTimeMillis());
		Contrat contrat = new Contrat(date,"CDI",2000);
		int referenceContrat = employeService.ajouterContrat(contrat);
		Assert.assertNotNull(employeService.getContratById(referenceContrat));
		
	}

}
