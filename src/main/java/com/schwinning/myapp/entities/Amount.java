package com.schwinning.myapp.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Amount {

	@Id
	@GeneratedValue
	private long id;
	
	@Column
	private String descripton;
	
	@Column
	private double amount;

	@Column
	private LocalDate dueDate;
	
	@ManyToOne
	private User user;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescripton() {
		return descripton;
	}

	public void setDescripton(String descripton) {
		this.descripton = descripton;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public Amount(long id, String descripton, double amount, LocalDate dueDate, User user) {
		super();
		this.id = id;
		this.descripton = descripton;
		this.amount = amount;
		this.dueDate = dueDate;
		this.user=user;
	}
	
	public Amount() {}
	
	
	
}
