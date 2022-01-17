package com.ameriprise.ATM.services;

import java.util.List;
import java.util.NoSuchElementException;

import com.ameriprise.ATM.repository.IAccountRepository;
import com.ameriprise.ATM.repository.ITransactionRepository;
import com.ameriprise.ATM.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ameriprise.ATM.models.Account;
import com.ameriprise.ATM.models.Transaction;
import com.ameriprise.ATM.models.User;

@Service
public class TransactionServiceImpl implements ITransactionService{
	
	@Autowired
	private ITransactionRepository transactionRepository;
	@Autowired
	private IUserRepository userRepository;
	@Autowired
	private IAccountRepository accountRepository;

	@Override
	@Transactional
	public List<Transaction> getTransactions(long userId, long accountId) {
		User user = this.userRepository.findById(userId).orElseThrow(NoSuchElementException::new);
		Account account =this.accountRepository.findById(accountId).orElseThrow(NoSuchElementException::new);
		return account.getTransactions();
	}

	@Override
	@Transactional
	public Transaction getTransactionById(long userId, long accountId, long transactionId) {
		if(userRepository.existsById(userId) && accountRepository.existsById(accountId)) {
			return transactionRepository.findById(transactionId).orElseThrow(NoSuchElementException::new);
		}
		throw new NoSuchElementException("userId or accountId provided is not correct");
	}

	@Override
	@Transactional(propagation = Propagation.MANDATORY)
	public Transaction addTransaction(Transaction transaction) {
		this.transactionRepository.save(transaction);
		return transaction;
	}

}
