package com.starco.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

}
