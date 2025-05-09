package br.com.banking.app.transaction.dto;

import java.time.OffsetDateTime;

import br.com.banking.app.transaction.model.Category;
import br.com.banking.app.transaction.model.TransactionStatus;
import br.com.banking.app.transaction.model.TransactionType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequestDTO {

    private long id;

    @Positive
    private double amount;

    @NotNull
    private OffsetDateTime time;

    @NotNull
    private Category category;

    private String description;

    private TransactionStatus status;

    private TransactionType type;

}
