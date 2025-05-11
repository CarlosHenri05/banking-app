package br.com.banking.app.transactions.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.banking.app.transactions.model.Transaction;
import br.com.banking.app.transactions.model.TransactionCategory;
import br.com.banking.app.transactions.model.TransactionType;
import br.com.banking.app.transactions.repository.TransactionRepository;
import br.com.banking.app.user.model.User;
import br.com.banking.app.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class TransactionService {
    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;

  public void saveTransaction(Transaction transaction, long id){
      User user = userRepository.findById(id)
                                .orElseThrow(() -> new IllegalArgumentException("User not found"));

    transaction.setUser(user);
    transactionRepository.save(transaction); 
  }

  public Transaction getTransactionById(long userId, long transactionId){
    Transaction transaction = transactionRepository.findById(transactionId).orElseThrow(() -> new RuntimeException("Transaction not found"));

     if (transaction.getUser() == null || !transaction.getUser().getId().equals(userId)) {
          throw new RuntimeException("User is not the owner of this transaction");
        }
    return transaction;
  }

  public List<Transaction> getAllTransactionsFromUser(long userId) {
      User user = userRepository.findById(userId)
          .orElseThrow(() -> new IllegalArgumentException("User not found"));


      return user.getTransactions().stream()
                                  .toList();
  }

  public List<Transaction> getAllTransactionsFromSameCategory(long userId, TransactionCategory category){
      User user = userRepository.findById(userId)
          .orElseThrow(() -> new IllegalArgumentException("User not found"));

      return user.getTransactions().stream()
                                  .filter(transaction -> transaction.getCategory() == category)
                                  .toList();
  }
  
  public List<Transaction> getTotalIncome(long userId){
    User user = userRepository.findById(userId)
                              .orElseThrow(() -> new IllegalArgumentException("User not found"));
    
    return user.getTransactions().stream()
                                 .filter(transaction -> transaction.getType() == TransactionType.INCOME)
                                 .toList();
  }

}
