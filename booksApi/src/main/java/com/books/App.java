package com.books;

import java.util.Scanner;

public class App {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    while (true) {
      System.out.println("Insira o nome do livro: ");
      String livro = scan.nextLine();

      try {
        VolumeInfo livroDTO = BooksService.fazerRequisicao(livro);
        System.out.println(livroDTO);
      } catch (Exception e) {
        System.out.println("Ocorreu um erro ao buscar o livro: " + e.getMessage());
      }
      System.out.println("Digite 0 para sair 1 para consultar livro");
      String entrada = scan.nextLine().trim();
      int opcao;
      try {
        opcao = Integer.parseInt(entrada);
      } catch (NumberFormatException e) {
        System.out.println("Entrada inválida.\n");
        continue;
      }
      if (opcao == 0) {
        System.out.println("Encerrando o programa. Até mais!");
        break;
      }
    }
    scan.close();
  }
}
