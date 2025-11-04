package com.books;

import java.io.IOException;
import java.util.Scanner;

public class App {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    while (true) {
      System.out.println("Insira o nome do livro: ");
      String livro = scan.nextLine();

      if (livro.isEmpty()) {
        System.out.println("Espaço em branco, insira o nome do livro");
        continue;
      }

      try {
        VolumeInfo livroDTO = BooksService.fazerRequisicao(livro);
        System.out.println(livroDTO);
      } catch (LivroNaoAchadoException e) {
        System.out.println("Ocorreu um erro ao buscar o livro: " + e.getMessage());
      } catch (IOException e) {
        if (e.getMessage().contains("Status 400")) {
          System.out.println("Erro de formato,  o livro " + livro + " é inválido.");
          System.out.println("Detalhe técnico: A API Google Books retornou 'Bad Request' (Status 400).");
          System.out.println("Insira o nome do livro novamente.");
        } else {
          System.out.println("Erro de leitura de dados, não foi possível completar a requisição.");
          System.out.println("Detalhes do erro: " + e.getMessage());
        }
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        System.out.println("A requisição foi interrompida, tente novamente.");
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
