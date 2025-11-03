package com.books;

import java.util.Scanner;

import com.google.gson.Gson;

public class App {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    // Pessoa pessoa = new Pessoa("Ana", 30, "São Paulo");
    //
    // Gson gson = new Gson();
    //
    // Pessoa p2 = gson.fromJson("{\"nome\":\"Carlos\",\"idade\":25,\"cidade\":\"Rio
    // de Janeiro\"}", Pessoa.class);
    //
    // System.out.println("Objeto Pessoa: " + p2);
    // System.out.println("Nome: " + p2.nome());
    // System.out.println("Idade: " + p2.idade());
    //
    // System.out.println(pessoa.nome());
    // System.out.println(pessoa.idade());
    // System.out.println(pessoa.cidade());

    while (true) {
      System.out.println("Insira o nome do livro: ");
      String livro = scan.nextLine();

      try {
        VolumeInfo livroDTO = BooksService.fazerRequisicao(livro);
        System.out.println(livroDTO);
      } catch (Exception e) {
        System.out.println("Ocorreu um erro ao buscar o livro: " + e.getMessage());
      }
      System.out.println("Digite 0 para sair 1 para consultar outro CEP:");
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
