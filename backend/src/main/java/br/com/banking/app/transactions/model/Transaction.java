package br.com.banking.app.transactions.model;


import br.com.banking.app.user.model.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "transactions")
public class Transaction {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "transaction_id")
  private long id;

  @ManyToOne
  @JoinColumn(nullable = false)
  private User user;

  
  @Positive
  @Column(nullable = false)
  private double amount;

  
  @Column(nullable = false)
  private String description;

  @Column(nullable = false)
  private TransactionType type;

  @Column(nullable = false)
  private TransactionCategory category;

  public Transaction(){
    
  }

  public Transaction(double amount, String description, TransactionType type, TransactionCategory category){
    this.amount = amount;
    this.description = description;
    this.type = type;
    this.category = category;
  }
}
