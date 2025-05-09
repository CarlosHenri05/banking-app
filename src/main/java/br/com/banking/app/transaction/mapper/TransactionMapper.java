package br.com.banking.app.transaction.mapper;

import java.util.ArrayList;
import java.util.List;

import br.com.banking.app.transaction.dto.TransactionRequestDTO;
import br.com.banking.app.transaction.dto.TransactionResponseDTO;
import br.com.banking.app.transaction.model.Transaction;
import br.com.banking.app.user.model.User;

public class TransactionMapper {

  public static Transaction toEntity(TransactionRequestDTO dto, User user) {
    return new Transaction(user, dto.getAmount(), dto.getTime(), dto.getDescription(),dto.getStatus(), dto.getType());
  }

  public static TransactionResponseDTO toResponse(Transaction transaction) {
    return new TransactionResponseDTO(transaction.getId(), transaction.getAmount(), transaction.getUser().getUsername(), transaction.getCreatedAt(), transaction.getCategory(), transaction.getDescription(), transaction.getType());
  }
  
  public static List<TransactionResponseDTO> transactionListToResponseList (List<Transaction> transactionList) {
    List<TransactionResponseDTO> list = new ArrayList<>();

    transactionList.forEach( transaction -> {
      list.add(toResponse(transaction));
    });
    
    return list;
  }
}


