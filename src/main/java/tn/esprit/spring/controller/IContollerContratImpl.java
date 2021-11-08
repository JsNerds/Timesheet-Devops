package tn.esprit.spring.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.repository.ContratRepository;
@Service
public class IContollerContratImpl implements ContratService {
	@Autowired
	ContratRepository contratRepository;
	private static final Logger l = LogManager.getLogger(IContollerContratImpl.class);
	
	@Override
	public List<Contrat> retrieveAllContrat() {
		List<Contrat> contrats=(List<Contrat>) contratRepository.findAll();
		for(Contrat Contrat: contrats){
            l.info(Contrat.getSalaire());
		}
		return contrats;
	}

	@Override
	public Contrat addContrat(Contrat u) {
		return contratRepository.save(u);
	}

	@Override
	public void deleteContrat(String id) {
		contratRepository.deleteById((int) Long.parseLong(id));
		
	}

	@Override
	public Contrat updateContrat(Contrat u) {
		return contratRepository.save(u);
	}

	@Override
	public Contrat retrieveContrat(String id) {
		return null;

	}

	public IContollerContratImpl() {
		super();
	}
}
