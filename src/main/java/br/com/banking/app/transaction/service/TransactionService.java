package br.com.banking.app.transaction.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.banking.app.transaction.model.Transaction;
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

  public static Double returnAccountBalance(User user){
    return user.getTransactions().stream()
                                 .mapToDouble(Transaction::getAmount)
                                 .sum();
  }

  



}
