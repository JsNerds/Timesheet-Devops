package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
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

	public static final Logger logger = Logger.getLogger(EmployeServiceImpl.class);

	@Autowired
	EmployeRepository employeRepository;
	@Autowired
	DepartementRepository deptRepoistory;
	@Autowired
	ContratRepository contratRepoistory;
	@Autowired
	TimesheetRepository timesheetRepository;
	@Autowired
	EntrepriseServiceImpl entrepriseService;

	public int ajouterEmploye(Employe employe) {
		logger.info("Starting add employe service");
		try {
			employeRepository.save(employe);
			if(logger.isDebugEnabled())
			{
			logger.debug(String.format("Saved new employe : %s", employe));
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		logger.info("The end of add employe service");
		return employe.getId();
	}

	public void mettreAjourEmailByEmployeId(String email, int employeId) {
		logger.info("Starting update email for employe service");
		try {
				Employe employe = getEmployeById(employeId);
				if(logger.isDebugEnabled())
				{
					logger.debug(String.format("Old mail: %s", employe.getEmail()));					
				}
				employe.setEmail(email);
				if(logger.isDebugEnabled())
				{
					logger.debug(String.format("New mail: %s", employe.getEmail()));					
				}
					employeRepository.save(employe);
					logger.debug("New mail saved");
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		logger.info("The end of update email for employe service");
	}

	@Transactional
	public void affecterEmployeADepartement(int employeId, int departementId) {
		logger.info("Starting of assign employe to department service");
		try {
				Departement depManagedEntity = entrepriseService.getDepartementById(departementId);
				Employe employeManagedEntity = getEmployeById(employeId);
				if (depManagedEntity.getEmployes() == null) {
					List<Employe> employes = new ArrayList<>();
					employes.add(employeManagedEntity);
					depManagedEntity.setEmployes(employes);
					if(logger.isDebugEnabled())
					{
						logger.debug(String.format("The first employe %s has been affected to the department %s",
								employeManagedEntity.getNom(),depManagedEntity.getName()));						
					}
				} else {
					depManagedEntity.getEmployes().add(employeManagedEntity);
					if(logger.isDebugEnabled())
					{
						logger.debug(String.format("Employe %s has been affected to the department %s",
								employeManagedEntity.getNom(),depManagedEntity.getName()));						
					}
				}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		logger.info("The end of asign employe to department service");
	}

	@Transactional
	public void desaffecterEmployeDuDepartement(int employeId, int departementId) {
		logger.info("Starting of dismissal of employe from department service");
		try {
				Departement dep = entrepriseService.getDepartementById(departementId);
				int employeNb = dep.getEmployes().size();
				for (int index = 0; index < employeNb; index++) {
					if (dep.getEmployes().get(index).getId() == employeId) {
						if(logger.isDebugEnabled())
						{
							logger.debug(String.format("Employe %s kicked from the department %s",
									dep.getEmployes().get(index).getNom(), dep.getName()));							
						}
						dep.getEmployes().remove(index);
						break;
					}
				}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		logger.info("The end of dismissal of employe from department service");
	}
	public int ajouterContrat(Contrat contrat) {
		try{
				contratRepoistory.save(contrat);
				if(logger.isDebugEnabled())
				{
					logger.debug(String.format("Saved new contract = %s" ,contrat.toString()));					
				}
			}catch (Exception e) {
				logger.error(e.getMessage());
			}
	return contrat.getReference();
	}

	public void affecterContratAEmploye(int contratId, int employeId) {
		logger.info("Starting for assign contract to employe service");
		try {
				Contrat contratManagedEntity = getContratById(contratId);
				Employe employeManagedEntity = getEmployeById(employeId);
				contratManagedEntity.setEmploye(employeManagedEntity);
				contratRepoistory.save(contratManagedEntity);
				if(logger.isDebugEnabled())
				{
					logger.debug(String.format("Contract %d affected to employe %s", contratManagedEntity.getReference(),
							employeManagedEntity.getNom()));
				}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		logger.info("The end of assign contract to employe service");
	}

	public String getEmployePrenomById(int employeId) {
		logger.info("Starting of retrieving the name of employe by id service");
		String testValidator = "empty";
		try {
			Employe employeManagedEntity = getEmployeById(employeId);
			testValidator = employeManagedEntity.getPrenom();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		logger.info("The end of retieving employe's name by id service");
		return testValidator;
	}

	public void deleteEmployeById(int employeId) {
		logger.info("Starting of delete employe by id service");
		try {
				Employe employe = getEmployeById(employeId);
				// Desaffecter l'employe de tous les departements
				// c'est le bout master qui permet de mettre a jour
				// la table d'association
				for (Departement dep : employe.getDepartements()) {
					dep.getEmployes().remove(employe);
					if(logger.isDebugEnabled())
					{
						logger.debug(String.format("Employe %s deleted from the department %s ",employe.getNom() ,dep.getName()));						
					}
				}
				employeRepository.delete(employe);

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		logger.info("The end of delete employe by id service");

	}

	public int getNombreEmployeJPQL() {
		logger.info("Retrieving the number of employes");
		return employeRepository.countemp();
	}

	public List<String> getAllEmployeNamesJPQL() {
		logger.info("Retrieving all employes' names");
		return employeRepository.employeNames();

	}

	public List<Employe> getAllEmployeByEntreprise(Entreprise entreprise) {
		logger.info("Retrieving all employes that are assigned to an department in a Company");
		return employeRepository.getAllEmployeByEntreprisec(entreprise);
	}

	public void mettreAjourEmailByEmployeIdJPQL(String email, int employeId) {
		logger.info("Updating employe's email by his/her id");
		employeRepository.mettreAjourEmailByEmployeIdJPQL(email, employeId);

	}

	public void deleteAllContratJPQL() {
		logger.info("deleting all contracts");
		employeRepository.deleteAllContratJPQL();
	}

	public float getSalaireByEmployeIdJPQL(int employeId) {
		logger.info("Retrieving Salary by employe's id");
		return employeRepository.getSalaireByEmployeIdJPQL(employeId);
	}

	public Double getSalaireMoyenByDepartementId(int departementId) {
		logger.info("Calculating average salary by department id");
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
		Optional<Employe> employe = employeRepository.findById(id);
        if (employe.isPresent()) {
        	if(logger.isDebugEnabled())
        	{
        		logger.debug(String.format("Entreprise exitse: %d", employe.get().getId()));        		
        	}
            return employe.get();
        }
        return null;
	}
	
	public Contrat getContratById(int id)
	{
		Optional<Contrat> contrat = contratRepoistory.findById(id);
        if (contrat.isPresent()) {
        	if(logger.isDebugEnabled())
        	{
        		logger.debug(String.format("contrat exitse: %d", contrat.get().getReference()));        		
        	}
            return contrat.get();
        }
        return null;
	}

}
