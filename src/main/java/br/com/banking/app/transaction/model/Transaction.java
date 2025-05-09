package br.com.banking.app.transaction.model;

import java.time.OffsetDateTime;


import br.com.banking.app.user.model.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "transactions")
@Getter
@Setter
public class Transaction {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  private double amount;

  private OffsetDateTime createdAt;

  private Category category;

  private String description;

  private TransactionStatus status; 

  private TransactionType type;

  public Transaction() {
    
  }

  public Transaction(User user, double amount, OffsetDateTime time, String description, TransactionStatus status, TransactionType type){
    this.user = user;
    this.amount = amount;
    this.createdAt = time;
    this.description = description;
    this.status = status;
    this.type = type;
  }


  public Transaction(User user, double amount, OffsetDateTime time, Category category, String description,TransactionStatus status, TransactionType type ){
    this.amount = amount;
    this.user = user;
    this.createdAt = time;
    this.category = category;
    this.description = description;
    this.status = status;
    this.type = type;
  }

}
