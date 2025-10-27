package com.receitasapi;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class App {
  public static void main(String[] args) throws IOException, InterruptedException {
    Scanner scan = new Scanner(System.in);
    System.out.println("Bem vindo ao consultor de receitas!");
    String nomeReceita;
    int opcao = -1;
    do {
      System.out.println("Qual receita você gostaria de consultar?");
      try {
        nomeReceita = scan.nextLine();
      } catch (InputMismatchException e) {
        System.out.println("Entrada inválida. Por favor, insira o nome da receita de maneira correta.");
        scan.next();
        continue; // Volta ao início do loop
      }
      System.out.println(fazerRequisicao(nomeReceita));
      try {
        System.out.println("Deseja consultar outra receita?");
        System.out.println("1 - Sim");
        System.out.println("0 - Não");

        opcao = scan.nextInt();
        scan.nextLine(); // Limpa o buffer do scanner se o cliente escolher consultar outra receita
        switch (opcao) {
          case 1:
            System.out.println("Você escolheu consultar outra receita.");
            break;

          case 0:
            System.out.println("Saindo do programa, até a próxima!");
            break;
          default:
            System.out.println("Opção inválida. Saindo do programa.");
            opcao = 0;
            break;
        }
      } catch (InputMismatchException e) {
        System.out.println("Entrada inválida. Tente novamente.");
        scan.nextLine(); // Limpa a entrada inválida
      }
    } while (opcao != 0);
    scan.close();
  }

  public static String fazerRequisicao(String nomeReceita) throws IOException, InterruptedException {
    String receitaCodificada = URLEncoder.encode(nomeReceita, StandardCharsets.UTF_8.toString());
    String urlFinal = "https://www.themealdb.com/api/json/v1/1/search.php?s=" + receitaCodificada;
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(urlFinal))
        .build();
    HttpResponse<String> response = client
        .send(request, HttpResponse.BodyHandlers.ofString());
    return processarResposta(response.body());
  }

  public static String processarResposta(String repostaJson) throws IOException {
    ObjectMapper mapper = new ObjectMapper();

    JsonNode node = mapper.readTree(repostaJson);

    JsonNode mealsNode = node.get("meals");

    if (mealsNode == null || mealsNode.size() == 0) {
      return "Nenhuma receita encontrada com o nome informado.";
    }

    JsonNode primeiraReceita = mealsNode.get(0);

    String titulo = primeiraReceita.get("strMeal").asText();

    String intrucoes = primeiraReceita.get("strInstructions").asText();

    StringBuilder resultado = new StringBuilder();
    resultado.append("Receita encontrada: ").append(titulo).append("\n\n");
    resultado.append("Título: ").append(titulo).append("\n\n");
    resultado.append("Instruções:\n").append(intrucoes).append("\n\n");

    return resultado.toString();

  }
}
