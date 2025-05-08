package br.com.banking.app.transaction.dto;

import java.time.OffsetDateTime;
import java.util.Locale.Category;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter 
public class TransactionResponseDTO {

  private long id;

  @Positive
  private double amount;

  @NotNull
  private String username;

  @NotNull
  private OffsetDateTime time;

  @NotNull
  private Category category;

  public TransactionResponseDTO(){
    
  }

  public TransactionResponseDTO(long id, @Positive double amount, @NotNull String username,
      @NotNull OffsetDateTime time, @NotNull Category category) {
    this.id = id;
    this.amount = amount;
    this.username = username;
    this.time = time;
    this.category = category;
  }

  
}
