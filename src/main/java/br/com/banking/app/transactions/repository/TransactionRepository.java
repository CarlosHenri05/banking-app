package br.com.banking.app.transactions.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.banking.app.transactions.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{

  Optional<Transaction> findById(long id);

}
