package br.com.banking.app.transaction.service;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

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

  public List<Transaction> returnTransactions (User user) {
    return user.getTransactions().stream()
                                 .toList();
  }

  public Transaction returnSpecificTransaction(User user, long id) throws RuntimeException{
    Transaction transaction = transactionRepository.findById(id).orElseThrow( () -> new RuntimeException("Id"));

    if (user.getId() == transaction.getUser().getId()){
      return transaction;
    }

    return null;
  }

  public Double returnHowMuchYouveSpent(User user){
    return user.getTransactions().stream()
                                 .filter(transaction -> transaction.getType() == TransactionType.EXPENSE)
                                 .mapToDouble(Transaction::getAmount)
                                 .sum();
  }

  public Double returnHowMuchYouveEarned(User user){
    return user.getTransactions().stream()
                                 .filter(transaction -> transaction.getType() == TransactionType.INCOME)
                                 .mapToDouble(Transaction::getAmount)
                                 .sum();
  }

  public List<Transaction> returnSpecificCategoryofTransactionList(User user, Category category){
    return user.getTransactions().stream()
                                 .filter(transaction -> transaction.getCategory().equals(category))
                                 .toList();
  }

  public List<Transaction> returnTransactionsInSpecificPeriodOfTime(User user, OffsetDateTime time){
    return user.getTransactions().stream()
                                 .filter(transaction -> transaction.getCreatedAt().equals(time))
                                 .toList();
    }  

  public void deleteTransaction(long id) {
    Transaction transaction = transactionRepository.findById(id).orElseThrow( ()-> new RuntimeException("Transaction not found"));

    transactionRepository.delete(transaction);
  }



}
