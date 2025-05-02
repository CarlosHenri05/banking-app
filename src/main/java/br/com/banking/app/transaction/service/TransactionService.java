package br.com.banking.app.transaction.service;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.banking.app.transaction.model.Transaction;
import br.com.banking.app.transaction.repository.TransactionRepository;
import br.com.banking.app.user.model.User;
import br.com.banking.app.user.repository.UserRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class TransactionService {
  
  private UserRepository userRepository;
  private TransactionRepository transactionRepository;

  public TransactionService (UserRepository userRepository, TransactionRepository transactionRepository) {
    this.userRepository = userRepository;
    this.transactionRepository = transactionRepository;
  }

  public void saveTransaction (Transaction transaction, String username) {
      User user = userRepository.findByEmail(username).orElseThrow( () ->  new RuntimeException("No user found")); 

      transaction.setUser(user);
      transactionRepository.save(transaction);
  }

  public Transaction returnTransaction (User user){
    Transaction tx = transactionRepository.findByEmail(user.getUsername()).orElseThrow( () -> new RuntimeException("Not found"));

    return tx;
  }

}
