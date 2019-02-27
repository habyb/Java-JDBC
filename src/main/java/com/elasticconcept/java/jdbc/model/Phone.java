package com.elasticconcept.java.jdbc.model;

public class Phone {

	private Long id;
	private String number;
	private String type;
	private Long personuser;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getPersonuser() {
		return personuser;
	}

	public void setPersonuser(Long personuser) {
		this.personuser = personuser;
	}

}
