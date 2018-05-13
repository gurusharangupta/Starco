package com.starco.app.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="PACKING")
public class Packing implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue
	private int id;
	
	@Column(nullable = false,unique=true)
	private String name;
	
	@Column
	private float weight;
	
	@Column
	private float price;
	
	@Column
	private float pricePerKg;
	
	@Column
	private float capacity;
	
	@Column
	private float quantity;
	
	
	@OneToMany(mappedBy="packing",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<ClientProductStarco> clientProductStarcoList;

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

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getPricePerKg() {
		return pricePerKg;
	}

	public void setPricePerKg(float pricePerKg) {
		this.pricePerKg = pricePerKg;
	}

	public List<ClientProductStarco> getClientProductStarcoList() {
		return clientProductStarcoList;
	}

	public void setClientProductStarcoList(
			List<ClientProductStarco> clientProductStarcoList) {
		this.clientProductStarcoList = clientProductStarcoList;
	}

	public float getCapacity() {
		return capacity;
	}

	public void setCapacity(float capacity) {
		this.capacity = capacity;
	}

	public float getQuantity() {
		return quantity;
	}

	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}
	
	

}
