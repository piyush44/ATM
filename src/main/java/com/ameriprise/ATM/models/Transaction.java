package com.ameriprise.ATM.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long transactionId;
	
	private double amount;
	
	private TransactionType type;
	
	private TransactionStatus status;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	private Account account;

	public Transaction() {
		super();
	}

	public Transaction(double amount, Account account) {
		this.amount = amount;				
		this.account = account;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public TransactionStatus getStatus() {
		return status;
	}

	public void setStatus(TransactionStatus status) {
		this.status = status;
	}

	public long getTransactionId() {
		return transactionId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public TransactionType getType() {
		return type;
	}

	public void setType(TransactionType type) {
		this.type = type;
	}

}
