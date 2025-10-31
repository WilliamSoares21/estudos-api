package com.cep;

import java.io.IOException;
import java.util.Scanner;

public class App {
  public static void main(String[] args) throws IOException, InterruptedException {

    Scanner scan = new Scanner(System.in);
    System.out.println("Seja bem vindo ao verifica CEP");
    System.out.println("Insira o cep desejado:");
    String cep = scan.nextLine();

    try {
      EnderecoDTO endereco = CepService.fazerRequisicao(cep);
      System.out.println(endereco);
    } catch (CepNotFoundException e) {
      System.out.println("Erro de busca, o CEP " + cep + " não foi encontrado.");
      System.out.println("Por gentileza, verifique o CEP e tente novamente.");
    } catch (IOException e) {
      if (e.getMessage().contains("Status 400")) {
        System.out.println("Erro de formato,  o CEP " + cep + " é inválido.");
        System.out.println("Detalhe técnico: A API ViaCEP retornou 'Bad Request' (Status 400).");
        System.out.println("Insira um CEP com 8 dígitos.");
      } else {
        System.out.println("Erro de leitura de dados, não foi possível completar a requisição.");
        System.out.println("Detalhes do erro: " + e.getMessage());
      }
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt(); // captura InterruptedException
      System.out.println("A requisição foi interrompida, tente novamente.");
    } finally {
      scan.close();
    }
  }
}
