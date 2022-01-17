package com.ameriprise.ATM.services;

import java.util.List;

import com.ameriprise.ATM.models.Account;
import com.ameriprise.ATM.models.InsufficientAmountException;
import com.ameriprise.ATM.models.Transaction;

public interface IAccountService {
	
	public List<Account> getAccounts(long userId);
	
	public Transaction withdraw(double withDrawAmount, Account account) throws InsufficientAmountException;
	
	public Transaction deposit(double depositAmount, Account account);
	
	public Account getAccountById(long userId, long accountId);
	
	public Account addOrUpdateAccount(Account account);
}
