package com.ameriprise.ATM.controller;

import java.util.List;
import java.util.NoSuchElementException;

import com.ameriprise.ATM.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ameriprise.ATM.models.Account;
import com.ameriprise.ATM.models.InsufficientAmountException;
import com.ameriprise.ATM.models.User;
import com.ameriprise.ATM.services.IAccountService;

@RestController
public class AccountController {
	
	@Autowired
	private IAccountService accountService;
	@Autowired
	private IUserRepository userRepository;
	
	// retrieves all accounts associated with the user 
	@GetMapping(value = "/users/{userId}/accounts")
	public List<Account> getAccounts(@PathVariable Long userId){		
		return this.accountService.getAccounts(userId);
	}
	
	// retrieve a single account by accountId
	@GetMapping(value = "/users/{userId}/accounts/{accountId}")
	public Account getAccountById(@PathVariable Long userId, @PathVariable Long accountId) {
		
		return this.accountService.getAccountById(userId, accountId);
	}
	
	@PutMapping(value = "users/{userId}/accounts/{accountId}/withdraw")
	public ResponseEntity<HttpStatus> withdraw(@RequestParam double amount, @PathVariable Long userId, @PathVariable Long accountId){
		try {			
			Account account = this.accountService.getAccountById(userId, accountId);
			this.accountService.withdraw(amount, account);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		} catch (InsufficientAmountException e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}		
	}
	
	@PutMapping(value = "users/{userId}/accounts/{accountId}/deposit")
	public ResponseEntity<HttpStatus> deposit(@RequestParam double amount, @PathVariable Long userId, @PathVariable Long accountId){
		try {			
			Account account = this.accountService.getAccountById(userId, accountId);
			this.accountService.deposit(amount, account);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
	@PostMapping(value = "/users/{userId}/accounts")
	public ResponseEntity<HttpStatus> addAccount (@PathVariable Long userId, @RequestBody Account account) {
		try {
			User user = userRepository.findById(userId).orElseThrow(NoSuchElementException::new);
			account.setUser(user);
			user.getAccounts().add(accountService.addOrUpdateAccount(account));
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping(value = "/users/{userId}/accounts")
	public ResponseEntity<HttpStatus> updateAccount (@RequestBody Account account){
		try {
			this.accountService.addOrUpdateAccount(account);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = "/users/{userId}/accounts/{accountId}/getBalance")
	public Double getBalance(@PathVariable Long userId, @PathVariable Long accountId) {
		Account acconut = this.accountService.getAccountById(userId, accountId);
		return acconut.getBalance();
	}
	
}
