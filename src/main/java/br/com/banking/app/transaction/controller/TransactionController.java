package br.com.banking.app.transaction.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.banking.app.transaction.dto.TransactionRequestDTO;
import br.com.banking.app.transaction.mapper.TransactionMapper;
import br.com.banking.app.transaction.model.Transaction;
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
}
