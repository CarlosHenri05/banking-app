package br.com.banking.app.transaction.dto;

import java.time.OffsetDateTime;
import java.util.Locale.Category;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class TransactionRequestDTO {

  private long id;

  @Positive
  private double amount;

  @NotNull
  private OffsetDateTime time;

  @NotNull
  private Category category;
}
