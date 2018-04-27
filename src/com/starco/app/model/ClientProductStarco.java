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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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
	
	@ManyToOne(cascade = CascadeType.REMOVE,fetch=FetchType.EAGER)
	@JoinColumn(name="product_id")
	private Product product;
	
	@ManyToOne(cascade = CascadeType.REMOVE,fetch=FetchType.EAGER)
	@JoinColumn(name="packing_id")
	private Packing packing;
	
	@Column
	private float price;
	
	@OneToMany(mappedBy="clientProductStarco",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<Sales> salesList;
	
	@ManyToOne(cascade = CascadeType.REMOVE,fetch=FetchType.EAGER)
	@JoinColumn(name="client_id")
	private Client client;

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

	public List<Sales> getSalesList() {
		return salesList;
	}

	public void setSalesList(List<Sales> salesList) {
		this.salesList = salesList;
	}
	
	
	

}
