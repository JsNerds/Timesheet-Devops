package tn.esprit.spring.controller;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.repository.ContratRepository;;
@Service
public class IContollerContratImpl implements ContratService {
	@Autowired
	ContratRepository ContratRepository ;
	private static final Logger l = LogManager.getLogger(IContollerContratImpl.class);
	
	@Override
	public List<Contrat> retrieveAllContrat() {
		List<Contrat> contrats=(List<Contrat>) ContratRepository.findAll();
		for(Contrat Contrat: contrats){
			l.info("contrat list : "+ Contrat);
		}
		return contrats;
	}

	@Override
	public Contrat addContrat(Contrat u) {
		// TODO Auto-generated method stub
		return ContratRepository.save(u);
	}
	
	@Override
	public Contrat getContratById(int id)
	{
		Optional<Contrat> contrat = ContratRepository.findById(id);
        if (contrat.isPresent()) {
        	if(l.isDebugEnabled())
        	{
        		l.debug(String.format("contrat exitse: %d", contrat.get().getReference()));        		
        	}
            return contrat.get();
        }
        return null;
	}
	@Override
	public int deleteContrat(int id) {
		ContratRepository.deleteById(id);
			return 1;
	}

	@Override
	public Contrat updateContrat(Contrat u) {
		return ContratRepository.save(u);
	}

	@Override
	public Contrat retrieveContrat(int id) {
		return null;

	}

	public IContollerContratImpl() {
		super();
		// TODO Auto-generated constructor stub
	}
}
