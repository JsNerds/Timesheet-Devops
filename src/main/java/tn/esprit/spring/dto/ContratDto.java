package tn.esprit.spring.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class ContratDto {
	
	private int reference;
	private float salaire;
	private Date dateDebut;
	private String dureecontrat;
	private String type;
	
	public ContratDto(int reference, Date dateDebut, String typeContrat, float salaire) {
		super();
		this.reference = reference;
		this.dateDebut = dateDebut;
		this.type = typeContrat;
		this.salaire = salaire;
	}
}
