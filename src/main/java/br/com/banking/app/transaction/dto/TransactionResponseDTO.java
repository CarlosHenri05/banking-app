package br.com.banking.app.transaction.dto;

import java.time.OffsetDateTime;


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
public class TransactionResponseDTO {

  private long id;

  @Positive
  private double amount;

  @NotNull
  private String username;

  @NotNull
  private OffsetDateTime time;
}
