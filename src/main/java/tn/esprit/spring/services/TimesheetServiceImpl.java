package tn.esprit.spring.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.entities.Timesheet;
import tn.esprit.spring.entities.TimesheetPK;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.repository.MissionRepository;
import tn.esprit.spring.repository.TimesheetRepository;

@Service
public class TimesheetServiceImpl implements ITimesheetService {

	private static final Logger l = LogManager.getLogger(EmployeServiceImpl.class);

	@Autowired
	MissionRepository missionRepository;
	@Autowired
	DepartementRepository departementRepository;
	@Autowired
	TimesheetRepository timesheetRepository;
	@Autowired
	EmployeRepository employeRepository;

	public int ajouterMission(Mission mission) {
		missionRepository.save(mission);
		l.info("mission ajouté avc nom : " + mission.getName());
		return mission.getId();

	}

	public Mission getMissionById(int missionId) {
		if (missionRepository.findById(missionId).isPresent()) {
			return missionRepository.findById(missionId).get();

		} else
			return null;
	}

	public int affecterMissionADepartement(int missionId, int depId) {
		Mission mission = missionRepository.findById(missionId).get();
		Departement dep = departementRepository.findById(depId).get();
		mission.setDepartement(dep);
		missionRepository.save(mission);
		l.info("mission " + mission.getName() + " ajouté au departement : " + dep.getName());

		return mission.getDepartement().getId();

	}

	public void ajouterTimesheet(int missionId, int employeId, Date dateDebut, Date dateFin) {
		TimesheetPK timesheetPK = new TimesheetPK();
		timesheetPK.setDateDebut(dateDebut);
		timesheetPK.setDateFin(dateFin);
		timesheetPK.setIdEmploye(employeId);
		timesheetPK.setIdMission(missionId);

		Timesheet timesheet = new Timesheet();
		timesheet.setTimesheetPK(timesheetPK);
		timesheet.setValide(false); // par defaut non valide
		timesheetRepository.save(timesheet);

		l.info("timesheet ajouté!");

	}

	public int validerTimesheet(int missionId, int employeId, Date dateDebut, Date dateFin, int validateurId) {
		System.out.println("In valider Timesheet");
		Employe validateur = employeRepository.findById(validateurId).get();
		Mission mission = missionRepository.findById(missionId).get();
		// verifier s'il est un chef de departement (interet des enum)
		if (!validateur.getRole().equals(Role.CHEF_DEPARTEMENT)) {
			System.out.println("l'employe doit etre chef de departement pour valider une feuille de temps !");
			return -1;
		}
		// verifier s'il est le chef de departement de la mission en question
		boolean chefDeLaMission = false;
		for (Departement dep : validateur.getDepartements()) {
			if (dep.getId() == mission.getDepartement().getId()) {
				chefDeLaMission = true;
				break;
			}
		}
		if (!chefDeLaMission) {
			System.out.println("l'employe doit etre chef de departement de la mission en question");
			return 0;
		}
		//
		TimesheetPK timesheetPK = new TimesheetPK(missionId, employeId, dateDebut, dateFin);
		Timesheet timesheet = timesheetRepository.findBytimesheetPK(timesheetPK);
		timesheet.setValide(true);
		l.info("timesheet validé!");

		// Comment Lire une date de la base de données
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println("dateDebut : " + dateFormat.format(timesheet.getTimesheetPK().getDateDebut()));

		return 1;

	}

	public List<Mission> findAllMissionByEmployeJPQL(int employeId) {
		return timesheetRepository.findAllMissionByEmployeJPQL(employeId);
	}

	public List<Employe> getAllEmployeByMission(int missionId) {
		return timesheetRepository.getAllEmployeByMission(missionId);
	}

}
