package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.entities.Timesheet;
import tn.esprit.spring.repository.ContratRepository;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.repository.TimesheetRepository;

@Service
public class EmployeServiceImpl implements IEmployeService {
	
	private static final Logger l = LogManager.getLogger(EmployeServiceImpl.class);
	
	@Autowired
	EmployeRepository employeRepository;
	@Autowired
	DepartementRepository deptRepoistory;
	@Autowired
	ContratRepository contratRepoistory;
	@Autowired
	TimesheetRepository timesheetRepository;

	public int ajouterEmploye(Employe employe) {
				try{
				if(employeRepository.save(employe) != null)
				{
					l.debug("Saved new employe = " + employe);
				}
				}catch (Exception e) {
					l.error(e.getMessage());
				}
		return employe.getId();
	}

	public void mettreAjourEmailByEmployeId(String email, int employeId) {
		try {
			if(employeRepository.findById(employeId).isPresent()){
				Employe employe = employeRepository.findById(employeId).get();
				l.debug("Old mail: "+ employe.getEmail());
				employe.setEmail(email);
				l.debug("New mail: "+ employe.getEmail());
				if(employeRepository.save(employe) != null)
				{
					l.debug("New mail saved");
				}
			}
			else
			{
				l.error("Employe doesn't exist");
			}
		} catch (Exception e) {
			l.error(e.getMessage());
		}
	}

	@Transactional	
	public void affecterEmployeADepartement(int employeId, int depId) {
		try {
			if(deptRepoistory.findById(depId).isPresent() && employeRepository.findById(employeId).isPresent())
			{	
			Departement depManagedEntity = deptRepoistory.findById(depId).get();
			Employe employeManagedEntity = employeRepository.findById(employeId).get();
			if(depManagedEntity.getEmployes() == null){
				List<Employe> employes = new ArrayList<>();
				employes.add(employeManagedEntity);
				depManagedEntity.setEmployes(employes);
				l.debug("The first employe "+ employeManagedEntity.getNom() +"has been affected to the department "
				+ depManagedEntity.getName());
			}else{
				depManagedEntity.getEmployes().add(employeManagedEntity);
				l.debug("Employe "+ employeManagedEntity.getNom() +"has been affected to the department "
						+ depManagedEntity.getName());
			}
			}
		} catch (Exception e) {
			l.error(e.getMessage());
		}
		

	
	}
	@Transactional
	public void desaffecterEmployeDuDepartement(int employeId, int depId)
	{
		try {
			if(deptRepoistory.findById(depId).isPresent())
			{
				Departement dep = deptRepoistory.findById(depId).get();
				int employeNb = dep.getEmployes().size();
				for(int index = 0; index < employeNb; index++){
					if(dep.getEmployes().get(index).getId() == employeId){
						l.debug("Employe" + dep.getEmployes().get(index).getNom() + "kicked from the department " + dep.getName() );
						dep.getEmployes().remove(index);
						break;
					}
				}
			}
			else
			{
				l.error("the departement does not exist");
			}
		} catch (Exception e) {
			l.error(e.getMessage());
		}
		
	}

	public Contrat getContratByReference(int reference) {
		return contratRepoistory.findById(reference).get();
	}
	
	public int ajouterContrat(Contrat contrat) {
		try{
			if(contratRepoistory.save(contrat) != null)
			{
				l.debug("Saved new contract = " + contrat);
			}
			}catch (Exception e) {
				l.error(e.getMessage());
			}
	return contrat.getReference();
	}
		
	public void affecterContratAEmploye(int contratId, int employeId) {
		try {
			Contrat contratManagedEntity = contratRepoistory.findById(contratId).get();
			Employe employeManagedEntity = employeRepository.findById(employeId).get();
			contratManagedEntity.setEmploye(employeManagedEntity);
			if(contratRepoistory.save(contratManagedEntity) != null){
				l.debug("Contract " + contratManagedEntity.getReference() + " affected to employe "+ employeManagedEntity.getNom());
			}
		} catch (Exception e) {
			l.error(e.getMessage());
		}
	}

	public String getEmployePrenomById(int employeId) {
		String testValidator = "empty";
		try {
			Employe employeManagedEntity = employeRepository.findById(employeId).get();
			testValidator = employeManagedEntity.getPrenom();
		} catch (Exception e) {
			l.error(e.getMessage());
		}
		return testValidator;
	}
	
	public void deleteEmployeById(int employeId)
	{
		try{
			if(employeRepository.findById(employeId).isPresent())
			{
				Employe employe = employeRepository.findById(employeId).get();
				//Desaffecter l'employe de tous les departements
				//c'est le bout master qui permet de mettre a jour
				//la table d'association
				for(Departement dep : employe.getDepartements()){
					dep.getEmployes().remove(employe);
					l.debug("Employe "+ employe.getNom() + " from the department "+ dep.getName());
				}
				employeRepository.delete(employe);
			}
			else
			{
				l.error("no such element");
			}
		}catch (Exception e) {
			l.error(e.getMessage());
		}
		
	}

	public void deleteContratById(int contratId) {
		try {
			Contrat contratManagedEntity = contratRepoistory.findById(contratId).get();
			contratRepoistory.delete(contratManagedEntity);
			l.debug("Contract "+ contratManagedEntity.getReference() +" deleted");
		} catch (Exception e) {
			l.error(e.getMessage());
		}
	}

	public int getNombreEmployeJPQL() {
		return employeRepository.countemp();
	}
	
	public List<String> getAllEmployeNamesJPQL() {
		return employeRepository.employeNames();

	}
	
	public List<Employe> getAllEmployeByEntreprise(Entreprise entreprise) {
		return employeRepository.getAllEmployeByEntreprisec(entreprise);
	}

	public void mettreAjourEmailByEmployeIdJPQL(String email, int employeId) {
		employeRepository.mettreAjourEmailByEmployeIdJPQL(email, employeId);

	}
	public void deleteAllContratJPQL() {
         employeRepository.deleteAllContratJPQL();
	}
	
	public float getSalaireByEmployeIdJPQL(int employeId) {
		return employeRepository.getSalaireByEmployeIdJPQL(employeId);
	}

	public Double getSalaireMoyenByDepartementId(int departementId) {
		return employeRepository.getSalaireMoyenByDepartementId(departementId);
	}
	
	public List<Timesheet> getTimesheetsByMissionAndDate(Employe employe, Mission mission, Date dateDebut,
			Date dateFin) {
		return timesheetRepository.getTimesheetsByMissionAndDate(employe, mission, dateDebut, dateFin);
	}

	public List<Employe> getAllEmployes() {
				return (List<Employe>) employeRepository.findAll();
	}

	@Override
	public Employe getEmployeById(int id) {
		if(employeRepository.findById(id).isPresent())
		{
			return employeRepository.findById(id).get();
		}
		else
		{
			return null;
		}
		
	}

}
