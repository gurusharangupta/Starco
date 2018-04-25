package com.starco.app.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="RAW_MATERIAL",uniqueConstraints = {
        @UniqueConstraint(columnNames =  {"name", "vendor_id"})})
public class RawMaterials implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	
	@Id
	@GeneratedValue
	@Column
	private int id;
	
	@Column
	private String name;
	
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="vendor_id",nullable=false)
	private Vendor vendor;
	
	@OneToOne(mappedBy="rawMaterials",cascade= CascadeType.REMOVE)
	private RawMaterialsStarco rawMaterialsStarco;
	
	@Column
	private float price;
	
	@Column
	private float ph;
	
	@Column
	private float solids;
	
	@Column
	private float viscosity;
	
	@Column
	private String appearance;
	
	@Column
	private String color;
	
	
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
	
	public float getPh() {
		return ph;
	}
	public void setPh(float ph) {
		this.ph = ph;
	}
	public float getSolids() {
		return solids;
	}
	public void setSolids(float solids) {
		this.solids = solids;
	}
	public float getViscosity() {
		return viscosity;
	}
	public void setViscosity(float viscosity) {
		this.viscosity = viscosity;
	}
	public String getAppearance() {
		return appearance;
	}
	public void setAppearance(String appearance) {
		this.appearance = appearance;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public float getPrice() {
		return price;
	}
	public RawMaterialsStarco getRawMaterialsStarco() {
		return rawMaterialsStarco;
	}
	public void setRawMaterialsStarco(RawMaterialsStarco rawMaterialsStarco) {
		this.rawMaterialsStarco = rawMaterialsStarco;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((vendor == null) ? 0 : vendor.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RawMaterials other = (RawMaterials) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (vendor == null) {
			if (other.vendor != null)
				return false;
		} else if (!vendor.equals(other.vendor))
			return false;
		return true;
	}
}
