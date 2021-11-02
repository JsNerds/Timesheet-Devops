package tn.esprit.spring.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import tn.esprit.spring.entities.TypeContrat;


@Data
@AllArgsConstructor
public class ContratDto {
	
	private String reference;
	private String salaire;
	private String DateDebut;
	private String dureecontrat;
	private TypeContrat type;
	
}
