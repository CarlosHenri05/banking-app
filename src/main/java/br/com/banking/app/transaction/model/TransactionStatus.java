package br.com.banking.app.transaction.model;

public enum TransactionStatus {
    PENDING,    // Transação pendente
    PLANNED,    // Transação planejada
    COMPLETED,  // Transação realizada
    CANCELLED,  // Transação cancelada
    OVERDUE     // Transação vencida
} 
