package br.com.banking.app.transaction.dto;

import java.time.OffsetDateTime;

import br.com.banking.app.transaction.model.Category;
import br.com.banking.app.transaction.model.TransactionType;
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

  @NotNull
  private String description;

  private TransactionType type;

  public TransactionResponseDTO(){
  }

  public TransactionResponseDTO(long id, @Positive double amount, @NotNull String username,
      @NotNull OffsetDateTime time, @NotNull Category category, String description, @NotNull TransactionType type) {
    this.id = id;
    this.amount = amount;
    this.username = username;
    this.time = time;
    this.category = category;
    this.description = description;
    this.type = type;
  }

  
}
