package br.com.banking.app.transactions.mapper;



import java.util.ArrayList;
import java.util.List;

import br.com.banking.app.transactions.dto.TransactionRequestDTO;
import br.com.banking.app.transactions.dto.TransactionResponseDTO;
import br.com.banking.app.transactions.model.Transaction;
import br.com.banking.app.transactions.model.TransactionCategory;
import br.com.banking.app.transactions.model.TransactionType;

public class TransactionMapper {

  public static Transaction toEntity(TransactionRequestDTO requestDTO){
    Transaction transaction = new Transaction(requestDTO.getAmount(), requestDTO.getDescription(),TransactionType.valueOf(requestDTO.getType().toUpperCase()),TransactionCategory.valueOf(requestDTO.getCategory().toUpperCase()) );

    return transaction;
  }

  public static TransactionResponseDTO toResponse(Transaction transaction){
    TransactionResponseDTO responseDTO = new TransactionResponseDTO(transaction.getId(),transaction.getAmount(), transaction.getDescription(), transaction.getType().toString(), transaction.getCategory().toString());

    return responseDTO;
  }

  public static List<TransactionResponseDTO> transactionListToResponseList(List<Transaction> listOfTransactions){
    List<TransactionResponseDTO> listOfResponses = new ArrayList<>();

    listOfTransactions.forEach(
      transaction -> {
        listOfResponses.add(toResponse(transaction));
      }
    );

    return listOfResponses;
  }
}
