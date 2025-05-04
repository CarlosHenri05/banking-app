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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "transactions")
@AllArgsConstructor
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

  private OffsetDateTime time;


  public Transaction(User user, double amount, OffsetDateTime time){
    this.amount = amount;
    this.user = user;
    this.time = time;
  }

}
