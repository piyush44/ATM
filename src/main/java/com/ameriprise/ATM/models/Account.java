package com.ameriprise.ATM.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Account {

	@Id
	private long accountId;
	private double balance;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "user_user_id")
	private User user;

	@OneToMany(mappedBy = "account")
	private List<Transaction> transactions;

	public Account() {
		super();
	}
	
	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", balance=" + balance + ", transactions=" + transactions + "]";
	}

	public Account(long accountId, double balance, User user) {
		this.accountId = accountId;
		this.balance = balance;
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}
	
	@Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
	public double getBalance() {
		return balance;
	}
	
	@Transactional(propagation = Propagation.MANDATORY)
	public void setBalance(double balance) {
		this.balance = balance;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

}
