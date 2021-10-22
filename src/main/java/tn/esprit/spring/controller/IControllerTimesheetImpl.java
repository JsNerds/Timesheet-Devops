package tn.esprit.spring.controller;

import java.util.Date;
import org.apache.log4j.Logger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.services.IEmployeService;
import tn.esprit.spring.services.IEntrepriseService;
import tn.esprit.spring.services.ITimesheetService;

@Controller
public class IControllerTimesheetImpl {
	private static final Logger l = Logger.getLogger(IControllerTimesheetImpl.class);
	@Autowired
	IEmployeService iemployeservice;
	@Autowired
	IEntrepriseService ientrepriseservice;
	@Autowired
	ITimesheetService itimesheetservice;

	public int ajouterMission(Mission mission) {
		
		itimesheetservice.ajouterMission(mission);
		l.info("mission" + mission.getName() +" ajouté");
		return mission.getId();
	}

	public void affecterMissionADepartement(int missionId, int depId) {
		l.info("mission affecté");
		itimesheetservice.affecterMissionADepartement(missionId, depId);

	}
	public void ajouterTimesheet(int missionId, int employeId, Date dateDebut, Date dateFin) {
		itimesheetservice.ajouterTimesheet(missionId, employeId, dateDebut, dateFin);

	}

	
	public void validerTimesheet(int missionId, int employeId, Date dateDebut, Date dateFin, int validateurId) {
		itimesheetservice.validerTimesheet(missionId, employeId, dateDebut, dateFin, validateurId);

	}
	public List<Mission> findAllMissionByEmployeJPQL(int employeId) {

		return itimesheetservice.findAllMissionByEmployeJPQL(employeId);
	}


	public List<Employe> getAllEmployeByMission(int missionId) {

		return itimesheetservice.getAllEmployeByMission(missionId);
	}
}
