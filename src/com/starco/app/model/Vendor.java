package com.starco.app.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="VENDOR")
public class Vendor {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="VENDOR_ID")
	private int id;
	
	
	@Column(name="VENDOR_NAME")
	private String name;
	
	@Column(name="VENDOR_CONTACT")
	private String contact;

	
	@Column(name="VENDOR_ADDRESS")
	private String address;

	@Column(name="VENDOR_RATING")
	private int rating;
	
	@OneToMany(mappedBy="vendor",cascade=CascadeType.ALL)
	private Set<RawMaterials> rawMaterial;
	
	
	

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

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public Set<RawMaterials> getRawMaterial() {
		return rawMaterial;
	}

	public void setRawMaterial(Set<RawMaterials> rawMaterial) {
		this.rawMaterial = rawMaterial;
	}

}
