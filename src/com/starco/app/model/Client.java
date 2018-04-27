package com.starco.app.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="CLIENT")
public class Client implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue
	private int id;
	
	@Column(nullable = false,unique=true)
	private String name;
	
	@OneToMany(mappedBy="client",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	private List<ClientProductStarco> clientProductList;
	
	@OneToMany(mappedBy="client",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	private List<Sales> salesList;
	
	@Column
	private String notes;
	
	@Column
	private boolean ogs;

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


	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public boolean isOgs() {
		return ogs;
	}

	public void setOgs(boolean ogs) {
		this.ogs = ogs;
	}

	public List<ClientProductStarco> getClientProductList() {
		return clientProductList;
	}

	public void setClientProductList(List<ClientProductStarco> clientProductList) {
		this.clientProductList = clientProductList;
	}

	public List<Sales> getSalesList() {
		return salesList;
	}

	public void setSalesList(List<Sales> salesList) {
		this.salesList = salesList;
	}
	
	
	
	
}
