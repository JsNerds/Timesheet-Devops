package tn.esprit.spring.dto;

public class DepartementDto {
	private int id;
	
	private String name;

	public DepartementDto() {
		super();
	}

	public DepartementDto(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
