package com.carland.entity;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="user")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;
	
	@Column(name="name")
	private String name;
	
	@Column(name="telephone_number")
	private String telephoneNumber;
	
	@Column(name="city")
	private String city;
	
	@Column(name="street")
	private String street;
	
	@Column(name="postal_code")
	private String postalCode;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "users_roles", 
	joinColumns = @JoinColumn(name = "user_id"), 
	inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Collection<Role> roles;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy="user")
	private Collection<Advert> adverts;
	
	public User(){
		
	}
	
	

	public User(String username, String password, String name, String telephoneNumber, String city,
			String street, String postalCode) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.telephoneNumber = telephoneNumber;
		this.city = city;
		this.street = street;
		this.postalCode = postalCode;
	}
	
	public User(int id, String username, String password, String name, String telephoneNumber, String city,
			String street, String postalCode, Collection<Role> roles, Collection<Advert> adverts) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.name = name;
		this.telephoneNumber = telephoneNumber;
		this.city = city;
		this.street = street;
		this.postalCode = postalCode;
		this.roles = roles;
		this.adverts = adverts;
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



	public String getTelephoneNumber() {
		return telephoneNumber;
	}



	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}



	public String getPostalCode() {
		return postalCode;
	}



	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}



	public Collection<Role> getRoles() {
		return roles;
	}



	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

	
	public Collection<Advert> getAdverts() {
		return adverts;
	}



	public void setAdverts(Collection<Advert> adverts) {
		this.adverts = adverts;
	}



	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", name=" + name
				+ ", telephoneNumber=" + telephoneNumber + ", city=" + city + ", street=" + street + ", postalCode="
				+ postalCode;
	}
	
	public Boolean hasRole(String role) {
		
		for(Role r : roles) {
			if(r.getName().equals("ROLE_" + role)) {
				return true;
			}
		}
		return false;
		
	}
	
	
	
}
