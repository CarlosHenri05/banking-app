package br.com.banking.app.transaction.service;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.banking.app.transaction.exceptions.ResourceNotFoundException;
import br.com.banking.app.transaction.model.Category;
import br.com.banking.app.transaction.model.Transaction;
import br.com.banking.app.transaction.model.TransactionType;
import br.com.banking.app.transaction.repository.TransactionRepository;
import br.com.banking.app.user.model.User;
import br.com.banking.app.user.repository.UserRepository;
import jakarta.transaction.Transactional;

@Service
public class TransactionService {
  
  private UserRepository userRepository;
  private TransactionRepository transactionRepository;

  public TransactionService (UserRepository userRepository, TransactionRepository transactionRepository) {
    this.userRepository = userRepository;
    this.transactionRepository = transactionRepository;
  }

  @Transactional
  public void saveTransaction (Transaction transaction, String username) {
      User user = userRepository.findByEmail(username).orElseThrow( () ->  new RuntimeException("No user found")); 

      transaction.setUser(user);
      transactionRepository.save(transaction);
  }

  public List<Transaction> getAllTransactions (long id) {
    User user = userRepository.findById(id).orElseThrow( ()-> new ResourceNotFoundException("Resource was not found in Database"));

    return user.getTransactions().stream()
                                 .toList();
  }

  public Transaction getTransactionById(long id, long idTransaction) throws RuntimeException{
    User user = userRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Resource not found"));
    
    Transaction transaction = transactionRepository.findById(idTransaction).orElseThrow( () -> new RuntimeException("Id"));

    if (user.getId() == transaction.getUser().getId()){
      return transaction;
    }

    return null;
  }

  public Double getTotalExpenses(long id){
    User user = userRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Resource not found"));

    return user.getTransactions().stream()
                                 .filter(transaction -> transaction.getType() == TransactionType.EXPENSE)
                                 .mapToDouble(Transaction::getAmount)
                                 .sum();
  }

  public Double getTotalIncome(long id){
    User user = userRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Resource not found"));

    return user.getTransactions().stream()
                                 .filter(transaction -> transaction.getType() == TransactionType.INCOME)
                                 .mapToDouble(Transaction::getAmount)
                                 .sum();
  }

  public List<Transaction> returnSpecificCategoryofTransactionList(long id, Category category){
    User user = userRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Resource not found"));


    return user.getTransactions().stream()
                                 .filter(transaction -> transaction.getCategory().equals(category))
                                 .toList();
  }

  public List<Transaction> returnTransactionsInSpecificPeriodOfTime(long id, OffsetDateTime time){
    User user = userRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Resource not found"));

    return user.getTransactions().stream()
                                 .filter(transaction -> transaction.getCreatedAt().equals(time))
                                 .toList();
    }  

  public void deleteTransaction(long id) {
    Transaction transaction = transactionRepository.findById(id).orElseThrow( ()-> new RuntimeException("Transaction not found"));

    transactionRepository.delete(transaction);
  }



}
