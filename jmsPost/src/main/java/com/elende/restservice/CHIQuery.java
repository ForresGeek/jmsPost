package com.elende.restservice;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "CHIQuery")
public class CHIQuery {

	
	
	
	
	/*
	 * 
	 * 
	 * {
  		"uniqueIdentifier": "ABC123",
  		"nhsNumber": "TEST123",
  		"forename": "Test",
  		"surname": "Person",
  		"DOB": "1948-01-01"
}

	 * 
	 * 
	 */
	
	
	
	
	
	
	private String uniqueIdentifier;
	private String nhsNumber;
	private String forename;
	private String surname;
	private String DOB;
	public String getUniqueIdentifier() {
		return uniqueIdentifier;
	}
	public void setUniqueIdentifier(String uniqueIdentifier) {
		this.uniqueIdentifier = uniqueIdentifier;
	}
	public String getNhsNumber() {
		return nhsNumber;
	}
	public void setNhsNumber(String nhsNumber) {
		this.nhsNumber = nhsNumber;
	}
	public String getForename() {
		return forename;
	}
	public void setForename(String forename) {
		this.forename = forename;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getDOB() {
		return DOB;
	}
	public void setDOB(String dOB) {
		DOB = dOB;
	}
	@Override
	public String toString() {
		return "CHIQuery ["
				+ (uniqueIdentifier != null ? "uniqueIdentifier="
						+ uniqueIdentifier + ", " : "")
				+ (nhsNumber != null ? "nhsNumber=" + nhsNumber + ", " : "")
				+ (forename != null ? "forename=" + forename + ", " : "")
				+ (surname != null ? "surname=" + surname + ", " : "")
				+ (DOB != null ? "DOB=" + DOB : "") + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
