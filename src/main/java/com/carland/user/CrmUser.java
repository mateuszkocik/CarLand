package com.carland.user;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class CrmUser {

	@Email(message="must have email format")
	@NotEmpty(message = "is required")
	private String username;
	
	@NotEmpty(message = "is required")
	@Size(min = 6, message = "Password must have more than 5 chars")
	private String password;
	
	@Size(min=3, message = "3 or letters are required")
	@Pattern(regexp="[a-zA-Z]+", message="Name must contain only letters")
	private String name;
	
	@NotEmpty(message="is required")
	@Pattern(regexp="[0-9]{9}", message="9 digits are required")
	private String telephoneNumber;
	
	@NotEmpty(message = "is required")
	@Pattern(regexp="[a-zA-Z]+", message="City must contain only letters")
	private String city;
	
	@NotEmpty(message = "is required")
	@Pattern(regexp="[a-zA-Z]+", message="Street must contain only letters")
	private String street;
	
	@NotEmpty(message = "is required")
	@Pattern(regexp="[0-9]{2}-[0-9]{3}", message="Postal code must be in XX-XXX format")
	private String postalCode;

	
	public CrmUser() {
		
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	
	
	
}
