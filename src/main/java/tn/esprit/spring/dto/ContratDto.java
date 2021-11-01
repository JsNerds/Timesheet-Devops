package tn.esprit.spring.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class ContratDto {
	
	private int reference;
	private Date dateDebut;
	private String typeContrat;
	private float salaire;
	
}
