package com.starco.app.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.starco.app.converter.LocalDateAttributeConverter;

@NamedQueries({
@NamedQuery(name = "findSalesForToday", query = "FROM Sales WHERE saleDate = :todaysDate"),
@NamedQuery(name = "findSalesForMonth", query = "FROM Sales WHERE saleDate <= :todaysDate and saleDate >= :beforeMonthDate"),
@NamedQuery(name = "findSalesForQuater", query = "FROM Sales WHERE saleDate <= :todaysDate and saleDate >= :beforeQuaterDate"),
@NamedQuery(name = "findSalesForYear", query = "FROM Sales WHERE saleDate <= :todaysDate and saleDate >= :beforeYearDate"),
})
@Entity
@Table(name="SALES")
public class Sales implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	
	@Id
	@GeneratedValue
	@Column
	private int id;
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="client_id")
	private Client client;
	
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="clientproductstarco_id")
	private ClientProductStarco clientProductStarco;
	
	@Column
	private LocalDate saleDate;
	
	@Column
	private float quantity;
	
	@Column
	private float carboys;
	
	@Column
	private float totalAmount;
	
	@Column
	private float cGST;
	
	@Column
	private float sGST;
	
	@Column
	private float iGST;
	
	@Column
	private float totalGST;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public ClientProductStarco getClientProductStarco() {
		return clientProductStarco;
	}

	public void setClientProductStarco(ClientProductStarco clientProductStarco) {
		this.clientProductStarco = clientProductStarco;
	}



	public float getQuantity() {
		return quantity;
	}

	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}

	public float getCarboys() {
		return carboys;
	}

	public void setCarboys(float carboys) {
		this.carboys = carboys;
	}

	public float getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}

	public float getcGST() {
		return cGST;
	}

	public void setcGST(float cGST) {
		this.cGST = cGST;
	}

	public float getsGST() {
		return sGST;
	}

	public void setsGST(float sGST) {
		this.sGST = sGST;
	}

	public float getiGST() {
		return iGST;
	}

	public void setiGST(float iGST) {
		this.iGST = iGST;
	}

	public float getTotalGST() {
		return totalGST;
	}

	public void setTotalGST(float totalGST) {
		this.totalGST = totalGST;
	}

	public LocalDate getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(LocalDate saleDate) {
		this.saleDate = saleDate;
	}
	
	
	
	
	
}
