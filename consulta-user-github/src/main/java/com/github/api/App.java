package com.github.api;

import java.util.Scanner;

public class App {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);

    while (true) {
      System.out.print("Insira o nome do user do github: ");
      String user = scan.nextLine();

      if (user.isEmpty()) {
        System.out.println("Espaço em branco, insira o username");
        continue;
      }
      try {
        Users usuario = ConsultaGitUserService.fazerRequisicao(user);
        System.out.println(usuario);
      } catch (ErroConsultaGitHubException e) {
        System.out.println("Erro na consulta -> " + e.getMessage());
      } catch (ErroRequisicaoException e) {
        System.out.println("Falha na Comunicação -> " + e.getMessage());
      } catch (Exception e) {
        System.out.println("Erro inesperado e crítico: " + e.getMessage());
      }
      System.out.println("---");
      System.out.println("Digite 0 para sair 1 para consultar um novo usuário");
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
