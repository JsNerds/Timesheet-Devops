package tn.esprit.spring.controller;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.ContratRepository;;

@Service
public class IContollerContratImpl implements ContratService {
	@Autowired
	ContratRepository ContratRepository;
	private static final Logger l = LogManager.getLogger(IContollerContratImpl.class);

	@Override
	public List<Contrat> retrieveAllContrat() {
		List<Contrat> contrats = (List<Contrat>) ContratRepository.findAll();
		for (Contrat Contrat : contrats) {
			l.info("contrat list : " + Contrat);
		}
		return contrats;
	}

	@Override
	public Contrat addContrat(Contrat u) {
		// TODO Auto-generated method stub
		return ContratRepository.save(u);
	}

	@Override
	public Contrat getContratById(int id) {
		Optional<Contrat> contrat = ContratRepository.findById(id);
		if (contrat.isPresent()) {
			if (l.isDebugEnabled()) {
				l.debug(String.format("contrat exitse: %d", contrat.get().getReference()));
			}
			return contrat.get();
		}
		return null;
	}

	@Override
	public int deleteContrat(int id) {
		l.info("START deleteEntrepriseById ");
		Optional<Contrat> e = ContratRepository.findById(id);

		try {

			l.trace("Début Test : verifier l'existence du contrat");
			if (e.isPresent()) {

				l.debug("contrat exitse:" + e.get().getReference());

				l.trace("débbut suppression");
				ContratRepository.delete(e.get());
				l.trace("fin suppression");
				l.trace("FIN Test");

				return 1;
			} else {
				l.trace("contrat n'exitse pas");
				l.trace("FIN Test avec fail");
				return -1;
			}

		} catch (Exception err) {
			l.error("err" + err);

		}
		if (e.isPresent()) {
			l.debug("Entrep supprimée:" + e.get().getReference());
		}

		l.info("END deleteEntrepriseById ");

		return 0;
	}

	@Override
	public Contrat updateContrat(Contrat u) {
		return ContratRepository.save(u);
	}

	@Override
	public Contrat retrieveContrat(int id) {
		Optional<Contrat> contrat = ContratRepository.findById(id);
		if (contrat.isPresent()) {
			if (l.isDebugEnabled()) {
				l.debug(String.format("contrat exitse: %d", contrat.get().getReference()));
			}
			return contrat.get();
		}
		return null;
	}

	public IContollerContratImpl() {
		super();
		// TODO Auto-generated constructor stub
	}
}
