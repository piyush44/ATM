package com.ameriprise.ATM.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {
	
	@Id	
	private Long userId;
	private String name;
	private String contact;
	private String email;	
	private String address;
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true  )	
	private List<Account> accounts;
	
	public User() {
		super();
	}	

	public User(long userId, String name, String contact, String email, String address) {
		this.userId = userId;
		this.name = name;
		this.contact = contact;
		this.email = email;
		this.address = address;		
	}
	
	

	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", contact=" + contact + ", email=" + email + ", address="
				+ address + "]";
	}

	public long getUserId() {
		return userId;
	}
	public void setUserId(long id) {
		userId = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public List<Account> getAccounts() {
		return accounts;
	}
	
	

}
