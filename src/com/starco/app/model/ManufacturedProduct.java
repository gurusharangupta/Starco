package com.starco.app.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@NamedQueries({
@NamedQuery(name = "findManufacturedProductForToday", query = "FROM ManufacturedProduct WHERE manufactureDate = :todaysDate"),
@NamedQuery(name = "findManufacturedProductForMonth", query = "FROM ManufacturedProduct WHERE manufactureDate <= :todaysDate and manufactureDate >= :beforeMonthDate"),
@NamedQuery(name = "findManufacturedProductForQuater", query = "FROM ManufacturedProduct WHERE manufactureDate <= :todaysDate and manufactureDate >= :beforeQuaterDate"),
@NamedQuery(name = "findManufacturedProductForYear", query = "FROM ManufacturedProduct WHERE manufactureDate <= :todaysDate and manufactureDate >= :beforeYearDate"),
})
@Entity
@Table(name="MANUFACTURED_PRODUCT")
public class ManufacturedProduct implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue
	@Column
	private int id;
	
	
	@OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinColumn(name="product_id")
	private Product product;
	
	@Column
	private float quantity;
	
	@Column
	private float yield;
	
	@Column
	private String place;
	
	@Column
	private String supervisor;
	
	@Column
	private String batchNumber;
	
	@Column
	private LocalDate manufactureDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public float getQuantity() {
		return quantity;
	}

	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}


	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}

	public String getBatchNumber() {
		return batchNumber;
	}

	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}

	public float getYield() {
		return yield;
	}

	public void setYield(float yield) {
		this.yield = yield;
	}

	public LocalDate getManufactureDate() {
		return manufactureDate;
	}

	public void setManufactureDate(LocalDate manufactureDate) {
		this.manufactureDate = manufactureDate;
	}

	
	
	
	
	

}
