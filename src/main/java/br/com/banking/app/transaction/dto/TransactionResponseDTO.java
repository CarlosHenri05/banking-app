package br.com.banking.app.transaction.dto;

import java.time.OffsetDateTime;

import br.com.banking.app.transaction.model.Category;
import br.com.banking.app.transaction.model.TransactionStatus;
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

    private String description;

    private TransactionStatus status;

    private OffsetDateTime dueDate;

    private boolean isPlanned;

    public TransactionResponseDTO() {
    }

    public TransactionResponseDTO(long id, double amount, String username,
            OffsetDateTime time, Category category, String description,
            TransactionStatus status, OffsetDateTime dueDate, boolean isPlanned) {
        this.id = id;
        this.amount = amount;
        this.username = username;
        this.time = time;
        this.category = category;
        this.description = description;
        this.status = status;
        this.dueDate = dueDate;
        this.isPlanned = isPlanned;
    }
}
