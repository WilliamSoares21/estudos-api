package com.github.api;

import java.util.Scanner;

public class App {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    System.out.print("Insira o nome do user do github: ");
    try {
      String user = scan.nextLine();
      Users usuario = ConsultaGitUserService.fazerRequisicao(user);
      System.out.println(usuario);
    } catch (ErroConsultaGitHubException e) {
      System.out.println("Erro na consulta -> " + e.getMessage());
    } catch (Exception e) {
      System.out.println("Ocorreu um erro inesperado: " + e.getMessage());
    } finally {
      scan.close();
    }
  }
}
