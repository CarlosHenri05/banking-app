package br.com.banking.app.transactions.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TransactionResponseDTO {

  private long transactionId;

  @NotNull
  private double amount;

  @NotNull
  private String description;

  @NotNull
  private String type;

  @NotNull
  private String category;
}
