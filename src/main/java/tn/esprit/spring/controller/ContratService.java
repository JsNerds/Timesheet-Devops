package tn.esprit.spring.controller;

import java.util.List;

import tn.esprit.spring.entities.Contrat;

public interface ContratService {
	List<Contrat> retrieveAllContrat();
	Contrat addContrat(Contrat u);
	boolean deleteContrat(int id);
	Contrat updateContrat(Contrat u);
	Contrat retrieveContrat(int id);
	Contrat getContratById(int id);
}
