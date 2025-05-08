package br.com.banking.app.transaction.controller;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.banking.app.transaction.dto.TransactionRequestDTO;
import br.com.banking.app.transaction.dto.TransactionResponseDTO;
import br.com.banking.app.transaction.mapper.TransactionMapper;
import br.com.banking.app.transaction.model.Transaction;
import br.com.banking.app.transaction.repository.TransactionRepository;
import br.com.banking.app.transaction.service.TransactionService;
import br.com.banking.app.user.model.User;
import br.com.banking.app.user.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transactions")
public class TransactionController {
  final TransactionService transactionService;
  final UserRepository userRepository;
  final TransactionRepository transactionRepository;


  @Operation (summary = "Realizar transações")
  @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Transação concluída e dados criados."),
            @ApiResponse(responseCode = "400", description = "Requisição inválida."),
            @ApiResponse(responseCode = "409", description = "Conflito ao processar a transação."),
            @ApiResponse(responseCode = "422", description = "Transação não concluída, possivelmente erro com valores inválidos.")
    })
  @PostMapping("/save")
  public ResponseEntity<Void> postTransaction(TransactionRequestDTO transactionRequestDTO) {
    User user = userRepository.findById(transactionRequestDTO.getId()).orElseThrow( () -> new RuntimeException("User not found"));

    Transaction tx = TransactionMapper.toEntity(transactionRequestDTO, user);

    transactionService.saveTransaction(tx, tx.getUser().getUsername());

    return ResponseEntity.status(201).build();
  }

  @Operation(summary = "Retornar todas as transações de um usuário")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "OK"),
    @ApiResponse(responseCode = "403", description = "Sem permissão para essa ação"),
    @ApiResponse(responseCode = "400", description = "ID não encontrado")
  })
  @GetMapping("/all")
  public ResponseEntity<List<TransactionResponseDTO>> getAllTransactions(@RequestParam long id) {
    User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

    List<Transaction> list = user.getTransactions();

    List<TransactionResponseDTO> listOfResponses = TransactionMapper.transactionListToResponseList(list);

    return ResponseEntity.ok(listOfResponses);
  }


  @GetMapping("/transaction{id}")
  @Operation(summary = "Retornar uma transação de id específico")
  public ResponseEntity<TransactionResponseDTO> returnSpecificTransation(@RequestParam long id, long idTransaction) {
    User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

    Transaction transaction = transactionService.returnSpecificTransaction(user, idTransaction);

    TransactionResponseDTO response = TransactionMapper.toResponse(transaction);

    return ResponseEntity.ok(response);
  }

  @GetMapping("/balance{id}")
  @Operation(summary = "Retornar o saldo da conta de um usuário específico")
  public ResponseEntity<Double> returnAccountBalance(@RequestParam long id){
    User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

    return ResponseEntity.ok(transactionService.returnHowMuchYouveSpent(user));
  }
}
