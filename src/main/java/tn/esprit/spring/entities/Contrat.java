package tn.esprit.spring.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity

@Table(name= "Contrat")
public class Contrat implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue (strategy= GenerationType.IDENTITY)
	private long id_Contrat;
	private String reference;
	private String salaire;
	private String DateDebut;
	private String dureecontrat;
	private TypeContrat TypeContrat;
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Employe", referencedColumnName = "id")
    private Employe Employe;
	
	
	
	
	
	
	public Contrat(long id_Contrat, String reference, String salaire, String dateDebut, String dureecontrat,
			tn.esprit.spring.entities.TypeContrat typeContrat, tn.esprit.spring.entities.Employe employe) {
		super();
		this.id_Contrat = id_Contrat;
		this.reference = reference;
		this.salaire = salaire;
		DateDebut = dateDebut;
		this.dureecontrat = dureecontrat;
		TypeContrat = typeContrat;
		Employe = employe;
	}
	public long getId_Contrat() {
		return id_Contrat;
	}
	public void setId_Contrat(long id_Contrat) {
		this.id_Contrat = id_Contrat;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public String getSalaire() {
		return salaire;
	}
	public void setSalaire(String salaire) {
		this.salaire = salaire;
	}
	public String getDateDebut() {
		return DateDebut;
	}
	public void setDateDebut(String dateDebut) {
		DateDebut = dateDebut;
	}
	public String getDureecontrat() {
		return dureecontrat;
	}
	public void setDureecontrat(String dureecontrat) {
		this.dureecontrat = dureecontrat;
	}
	public TypeContrat getTypeContrat() {
		return TypeContrat;
	}
	public void TypeContrat(TypeContrat TypeContrat) {
		this.TypeContrat = TypeContrat;
	}
	public Employe getEmploye() {
		return Employe;
	}
	public void setEmploye(Employe employe) {
		Employe = employe;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Contrat [id_Contrat=" + id_Contrat + ", reference=" + reference + ", salaire=" + salaire
				+ ", DateDebut=" + DateDebut + ", dureecontrat=" + dureecontrat + ", type=" + TypeContrat + ", Employe="
				+ Employe + "]";
	}
	
	
	

}
