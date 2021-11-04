package tn.esprit.spring.services;

import java.util.Date;
import java.util.List;

import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.entities.Timesheet;

public interface ITimesheetService {

	int ajouterMission(Mission mission);

	Mission getMissionById(int missionId);

	int affecterMissionADepartement(int missionId, int depId);

	Timesheet ajouterTimesheet(int missionId, int employeId, Date dateDebut, Date dateFin);

	int validerTimesheet(int missionId, int employeId, Date dateDebut, Date dateFin, int validateurId);

	List<Mission> findAllMissionByEmployeJPQL(int employeId);

	List<Employe> getAllEmployeByMission(int missionId);
}
