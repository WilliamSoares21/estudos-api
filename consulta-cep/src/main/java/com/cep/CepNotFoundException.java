package com.cep;

public class CepNotFoundException extends Exception {
  public CepNotFoundException(String message) {
    super(message);
  }

  public CepNotFoundException() {
    super("O cep fornecido não foi encontrado");
  }
}
