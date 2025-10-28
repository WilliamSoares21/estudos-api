package com.cep;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class App {
  public static void main(String[] args) throws IOException, InterruptedException {
    Scanner scan = new Scanner(System.in);
    System.out.println("Seja bem vindo ao verifica CEP");
    System.out.println("Insira o cep desejado:");
    String cep = scan.nextLine();

    System.out.println(fazerRequisicao(cep));
    scan.close();
  }

  public static String lerJson(HttpResponse<String> response) throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    String json = response.body();

    JsonNode node = mapper.readTree(json);

    return "teste cep:" + node;

  }

  public static String fazerRequisicao(String cep) throws IOException, InterruptedException {
    String url = "https://viacep.com.br/ws/" + cep + "/json/";
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(url))
        .build();
    HttpResponse<String> response = client
        .send(request, HttpResponse.BodyHandlers.ofString());
    return lerJson(response);

  }
}
