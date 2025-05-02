package br.com.banking.app.transaction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.banking.app.transaction.model.Transaction;
import br.com.banking.app.user.model.User;

import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

  Optional<Transaction> findById(long id);

  Optional<Transaction> findByEmail(String username);

  Optional<Transaction>findByUser(User user);

  Transaction getById(long id);

}
