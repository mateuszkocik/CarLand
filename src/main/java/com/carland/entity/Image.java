package com.carland.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="image")
public class Image {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@ManyToOne
	@JoinColumn(name="advert", nullable=false)
	private Advert advert;
	
	
	public Image() {
		
	}
	
	public Image(Advert advert, String name) {
		this.advert = advert;
		this.name = name;
	}
	

	public Image(int id, String name, Advert advert) {
		this.id = id;
		this.name = name;
		this.advert = advert;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Advert getAdvert() {
		return advert;
	}

	public void setAdvert(Advert advert) {
		this.advert = advert;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Image [advert=" + advert + ", name=" + name + "]";
	}
	
	
}
