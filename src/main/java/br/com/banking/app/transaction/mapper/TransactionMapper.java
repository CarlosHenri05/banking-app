package br.com.banking.app.transaction.mapper;

import java.util.ArrayList;
import java.util.List;

import br.com.banking.app.transaction.dto.TransactionRequestDTO;
import br.com.banking.app.transaction.dto.TransactionResponseDTO;
import br.com.banking.app.transaction.model.Transaction;
import br.com.banking.app.user.model.User;

public class TransactionMapper {

  public static Transaction toEntity(TransactionRequestDTO dto, User user) {
    return new Transaction(user, dto.getAmount(), dto.getTime());
  }

  public static TransactionResponseDTO toResponse(Transaction transaction) {
    return new TransactionResponseDTO(
        transaction.getId(),
        transaction.getAmount(),
        transaction.getUser().getUsername(),
        transaction.getTime()
    );
  }
  
  public static List<TransactionResponseDTO> transactionListToResponseList (List<Transaction> transactionList) {
    List<TransactionResponseDTO> list = new ArrayList<>();

    for(Transaction tr : transactionList) {
      list.add(toResponse(tr));
    }
    return list;
  }
}


