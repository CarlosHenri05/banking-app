package br.com.banking.app.transaction.exceptions;

public class ResourceNotFoundException extends RuntimeException {


  public ResourceNotFoundException(String message){
    super(message);
  }

}
