package com.cripto;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class App {
  public static void main(String[] args) throws IOException, InterruptedException {

    Scanner scan = new Scanner(System.in);
    System.out.println("Bem vindo ao consultor de criptomoedas!");

    while (true) {
      System.out.println("Qual criptomoeda você gostaria de consultar?");
      System.out.println("1 - BitCoin");
      System.out.println("2 - Ethereum (ETH)");
      System.out.println("0 - sair");
      int opcao;
      try {
        opcao = scan.nextInt();
      } catch (InputMismatchException e) {
        System.out.println("Entrada inválida. Por favor, insira um número.");
        scan.next(); // Limpa a entrada inválida
        continue; // Volta ao início do loop
      }
      if (opcao == 0) {
        System.out.println("Saindo do programa, até a próxima!");
        break;
      }

      switch (opcao) {
        case 1:
          System.out.println("Você escolheu BitCoin.");
          System.out.println(fazerRequisicao("bitcoin"));
          break;
        case 2:
          System.out.println("Você escolheu Eterium (ETH).");
          System.out.println(fazerRequisicao("ethereum"));
          /* System.out.println(lerJson(response2)); */
          break;
        default:
          System.out.println("Opção inválida. Por favor, escolha 1 ou 2.");
          continue; // Volta ao início do loop
      }
    }
    scan.close();
  }

  public static String lerJson(HttpResponse<String> response, String coinId) throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    String json = response.body();

    JsonNode jsonNode = mapper.readTree(json);

    JsonNode coinNode = jsonNode.get(coinId);
    double usd = coinNode.get("usd").asDouble();

    return "O valor da contação de " + coinId + " em USD é:" + usd + "\n";
  }

  public static String fazerRequisicao(String coinId) throws IOException, InterruptedException {
    String urlFinal = "https://api.coingecko.com/api/v3/simple/price?ids=" + coinId + "&vs_currencies=usd";
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(urlFinal))
        .build();
    HttpResponse<String> response = client
        .send(request, HttpResponse.BodyHandlers.ofString());
    return lerJson(response, coinId);
  }

}
