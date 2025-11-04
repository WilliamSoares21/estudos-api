package com.books;

public class LivroNaoAchadoException extends Exception {
  public LivroNaoAchadoException(String message) {
    super(message);
  }

  public LivroNaoAchadoException() {
    super("O livro não foi encontrado, verifique se foi escrito da maneira correta");
  }

}
