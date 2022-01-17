package com.ameriprise.ATM.services;

import java.util.List;
import java.util.NoSuchElementException;

import com.ameriprise.ATM.repository.IAccountRepository;
import com.ameriprise.ATM.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ameriprise.ATM.models.Account;
import com.ameriprise.ATM.models.InsufficientAmountException;
import com.ameriprise.ATM.models.Transaction;
import com.ameriprise.ATM.models.TransactionStatus;
import com.ameriprise.ATM.models.TransactionType;
import com.ameriprise.ATM.models.User;

@Service
public class AccountServiceImpl implements IAccountService {

	@Autowired
	private IAccountRepository accountRepository;

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private ITransactionService transactionService;

	public AccountServiceImpl() {

	}

	@Override
	@Transactional
	public List<Account> getAccounts(long userId) {
		if (userRepository.existsById(userId)) {
			User user = userRepository.findById(userId).orElseThrow(NoSuchElementException::new);
			return user.getAccounts();
		}
		throw new NoSuchElementException("userId provided is not correct");
	}

	@Override
	@Transactional(rollbackFor = Exception.class, noRollbackFor = InsufficientAmountException.class)
	public Transaction withdraw(double withDrawAmount, Account account) throws InsufficientAmountException {
		Transaction transaction = new Transaction(withDrawAmount, account);
		transaction.setType(TransactionType.WITHDRAW);
		List<Transaction> transactions = account.getTransactions();
		if (account.getBalance() >= withDrawAmount) {
			transaction.setStatus(TransactionStatus.SUCCESS);
			account.setBalance(account.getBalance() - withDrawAmount);
			transactions.add(transactionService.addTransaction(transaction));			
			account.setTransactions(transactions);
			addOrUpdateAccount(account);
		} else {
			transaction.setStatus(TransactionStatus.FAIL);
			throw new InsufficientAmountException("Insufficient balance in account to withdraw cash.");
		}
		return transaction;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Transaction deposit(double depositAmount, Account account) {
		Transaction transaction = new Transaction(depositAmount, account);
		transaction.setType(TransactionType.DEPOSIT);
		List<Transaction> transactions = account.getTransactions();	
		account.setBalance(account.getBalance() + depositAmount);
		transaction.setStatus(TransactionStatus.SUCCESS);
		transactions.add(transactionService.addTransaction(transaction));			
		account.setTransactions(transactions);
		addOrUpdateAccount(account);

		return transaction;
	}

	@Override
	@Transactional
	public Account getAccountById(long userId, long accountId) {
		if (userRepository.existsById(userId)) {
			return this.accountRepository.findById(accountId).orElseThrow(NoSuchElementException::new);
		}
		throw new NoSuchElementException("userId provided is not correct");
	}

	@Override
	@Transactional
	public Account addOrUpdateAccount(Account account) {
		this.accountRepository.save(account);
		return account;
	}

}
