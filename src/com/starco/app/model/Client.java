package com.starco.app.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
	private Set<ClientProductStarco> clientProductList;
	
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

	public Set<ClientProductStarco> getClientProductList() {
		return clientProductList;
	}

	public void setClientProductList(Set<ClientProductStarco> clientProductList) {
		this.clientProductList = clientProductList;
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
	
	
	
	
}
