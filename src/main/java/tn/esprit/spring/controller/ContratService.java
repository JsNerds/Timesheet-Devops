package tn.esprit.spring.controller;

import java.util.List;

import tn.esprit.spring.entities.Contrat;

public interface ContratService {
	List<Contrat> retrieveAllContrat();
	Contrat addContrat(Contrat u);
	void deleteContrat(String id);
	Contrat updateContrat(Contrat u);
	Contrat retrieveContrat(String id);
}
