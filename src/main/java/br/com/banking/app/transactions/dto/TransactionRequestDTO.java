package br.com.banking.app.transactions.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionRequestDTO {

 
  @NotNull
  private double amount;
  
  @NotNull
  private String description;

  @NotNull
  private String type;

  @NotNull
  private String category;

}
