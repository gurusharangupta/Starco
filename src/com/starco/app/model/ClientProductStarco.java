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
import javax.persistence.Table;

@Entity
@Table(name="CLIENT_PRODDUCT_STARCO")
public class ClientProductStarco implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue
	private int id;
	
	@Column(nullable = false,unique=true)
	private String name;
	
	@ManyToOne(cascade = CascadeType.REMOVE,fetch=FetchType.EAGER)
	@JoinColumn(name="product_id")
	private Product product;
	
	@ManyToOne(cascade = CascadeType.REMOVE,fetch=FetchType.EAGER)
	@JoinColumn(name="packing_id")
	private Packing packing;
	
	private float price;
	
	@ManyToOne(cascade = CascadeType.REMOVE,fetch=FetchType.EAGER)
	@JoinColumn(name="client_id")
	private Client client;

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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Packing getPacking() {
		return packing;
	}

	public void setPacking(Packing packing) {
		this.packing = packing;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	
	
	

}
