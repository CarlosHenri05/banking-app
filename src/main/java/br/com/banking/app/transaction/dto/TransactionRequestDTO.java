package br.com.banking.app.transaction.dto;

import java.time.OffsetDateTime;

import br.com.banking.app.user.model.User;
import io.micrometer.common.lang.NonNull;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequestDTO {
  
  @NonNull
  @Positive
  private double amount;

  @NotNull
  private User user;

  @NotNull 
  private OffsetDateTime time;
}
