package br.com.banking.app.transactions.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.banking.app.transactions.dto.TransactionRequestDTO;
import br.com.banking.app.transactions.dto.TransactionResponseDTO;
import br.com.banking.app.transactions.mapper.TransactionMapper;
import br.com.banking.app.transactions.model.Transaction;
import br.com.banking.app.transactions.model.TransactionCategory;
import br.com.banking.app.transactions.service.TransactionService;
import br.com.banking.app.user.model.User;
import br.com.banking.app.user.repository.UserRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

  private final TransactionService transactionService;
  private final UserRepository userRepository;

  @PostMapping("/save/{userId}")
  public ResponseEntity<Void> postTransaction(@PathVariable long userId, @RequestBody @Valid TransactionRequestDTO requestDTO) {

    User user = userRepository.findById(userId)
        .orElseThrow(() -> new IllegalArgumentException("User not found"));

    Transaction transaction = TransactionMapper.toEntity(requestDTO);

    transactionService.saveTransaction(transaction, user.getId());
    return ResponseEntity.status(201).build();
  }

  @GetMapping("/user/{userId}/transaction/{transactionId}")
  public ResponseEntity<TransactionResponseDTO> getTransactionById(@PathVariable long userId, @PathVariable long transactionId) {

    TransactionResponseDTO responseDTO = TransactionMapper.toResponse(
        transactionService.getTransactionById(userId, transactionId));

    return ResponseEntity.ok(responseDTO);
  }

  @GetMapping("/all/{userId}")
  public ResponseEntity<List<TransactionResponseDTO>> getAllTransactionsFromUser(@PathVariable long userId){
    List<Transaction> listOfTransactions = transactionService.getAllTransactionsFromUser(userId);

    List<TransactionResponseDTO> listOfResponses = TransactionMapper.transactionListToResponseList(listOfTransactions);

    return ResponseEntity.ok(listOfResponses);
  }

  @GetMapping("/all/{userId}/{category}")
  public ResponseEntity<List<TransactionResponseDTO>> getAllTransactionsFromSameCategory (@PathVariable long userId, @RequestParam TransactionCategory category){
    List<Transaction> listOfTransactions = transactionService.getAllTransactionsFromSameCategory(userId, category);
    
    List<TransactionResponseDTO> listOfResponses = TransactionMapper.transactionListToResponseList(listOfTransactions);

    return ResponseEntity.ok(listOfResponses);
  }

  @GetMapping("/{userId}/income")
  public ResponseEntity<Double> getTotalIncome(@PathVariable long userId){
    Double sumOfIncomes = transactionService.getTotalIncome(userId);

    return ResponseEntity.ok(sumOfIncomes);
  }

  @GetMapping("/{userId}/expenses")
  public ResponseEntity<Double> getTotalExpenses(@PathVariable long userId){
    Double sumOfExpenses = transactionService.getTotalExpenses(userId);

    return ResponseEntity.ok(sumOfExpenses);
  }

  @GetMapping("/{userId}/balance")
  public ResponseEntity<Double> getAccountBalance(@PathVariable long userId){
    Double accountBalance = transactionService.getTotalBalance(userId);

    return ResponseEntity.ok(accountBalance);
  }

}
