package com.carland.entity;

import java.time.LocalDate;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="advert")
public class Advert {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="user",nullable=false)
	private User user;
	
	
	@Column(name="title")
	@NotEmpty(message="title is required")
	@Size(max=32, message="title ")
	private String title;
	
	@Column(name="type")
	@Pattern(regexp="[^none]", message="type is required")
	private String type;
	
	@Column(name="make")
	@Pattern(regexp="[^none]", message="make is required")
	private String make;
	
	@Column(name="model")
	@Pattern(regexp="[^none]", message="model is required")
	private String model;
	
	@Column(name="price")
	@NotNull(message="price is required")
	//@Pattern(regexp="[0-9]+", message="invalid number")
	private Integer price;
	
	@Column(name="year")
	@NotNull(message="year is required")
	@Min(value=1900, message="year must be greater than 1900")
	@Max(value=2020, message="year must be lower than 2020")
	private Integer year;
	
	@Column(name="mileage")
	@NotNull(message="year is required")
	@PositiveOrZero(message="mileage must be positive")
	private Integer mileage;
	
	@Column(name="fuel_type")
	@Pattern(regexp="[^none]", message="make is required")
	private String fuelType;
	
	@Column(name="description")
	private String description;
	
	@Column(name="expiration_date")
	private LocalDate expirationDate;
	
	@Column(name="state")
	private String state;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy="advert")
	private Collection<Image> images;
	
	public Advert(){
		
	}

	public Advert(User user, String title, String type, String make, String model, int price, int year,
			int mileage, String fuelType, String description, LocalDate expirationDate, String state) {
		this.user = user;
		this.title = title;
		this.type = type;
		this.make = make;
		this.model = model;
		this.price = price;
		this.year = year;
		this.mileage = mileage;
		this.fuelType = fuelType;
		this.description = description;
		this.expirationDate = expirationDate;
		this.state = state;
	}
	
	public Advert(int id, User user, String title, String type, String make, String model, int price, int year,
			int mileage, String fuelType, String description, LocalDate expirationDate, String state, Collection<Image> images) {
		this.id = id;
		this.user = user;
		this.title = title;
		this.type = type;
		this.make = make;
		this.model = model;
		this.price = price;
		this.year = year;
		this.mileage = mileage;
		this.fuelType = fuelType;
		this.description = description;
		this.expirationDate = expirationDate;
		this.state = state;
		this.images = images;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMileage() {
		return mileage;
	}

	public void setMileage(int mileage) {
		this.mileage = mileage;
	}

	public String getFuelType() {
		return fuelType;
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(LocalDate expirationDate) {
		this.expirationDate = expirationDate;
	}
	
	public Collection<Image> getImages() {
		return images;
	}

	public void setImages(Collection<Image> images) {
		this.images = images;
	}
	

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "Advert [id=" + id + ", user=" + user + ", title=" + title + ", type=" + type + ", make=" + make
				+ ", model=" + model + ", price=" + price + ", year=" + year + ", mileage=" + mileage + ", fuelType="
				+ fuelType + ", describtion=" + description + ", expirationDate=" + expirationDate + "]";
	}
	
	
	
	
	
}
