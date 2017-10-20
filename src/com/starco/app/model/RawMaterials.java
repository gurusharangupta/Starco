package com.starco.app.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="RAW_MATERIAL")
public class RawMaterials {

	
	@Id
	@GeneratedValue
	@Column(name="RAW_MATERIAL_ID")
	private int id;
	
	@Column(name="RAW_MATERIALNAME")
	private String name;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Vendor vendor;
	
	@Column(name="PRICE")
	private int price;
	
	
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
	public Vendor getVendor() {
		return vendor;
	}
	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
}
