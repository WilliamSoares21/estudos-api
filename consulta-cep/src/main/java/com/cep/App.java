package com.cep;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
  public static void main(String[] args) throws IOException, InterruptedException {
    List<EnderecoDTO> arquivosJson = new ArrayList<>();
    Scanner scan = new Scanner(System.in);
    System.out.println("Seja bem vindo ao verifica CEP");

    while (true) {
      System.out.println("Insira o cep desejado:");
      String cep = scan.nextLine().trim();

      if (cep.isEmpty() || !cep.matches("\\d{8}")) {
        System.out.println("Por gentileza, insira um CEP válido com 8 dígitos numéricos.");
        continue;
      }

      try {
        EnderecoDTO endereco = CepService.fazerRequisicao(cep);
        System.out.println(endereco);
        arquivosJson.add(endereco);
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
        System.out.println("\nEncerrando o programa. Até mais!");
        break;
      }
    }
    JsonFileWriter.gravarEnderecoEmJson(arquivosJson);
    System.out.println("\nCeps consultados");
    for (EnderecoDTO arquivoJson : arquivosJson) {
      System.out.println(arquivoJson);
      System.out.println("\n");
    }
    System.out.println("Obrigador por usar o consulta CEO, volte sempre!");
    scan.close();
  }
}
