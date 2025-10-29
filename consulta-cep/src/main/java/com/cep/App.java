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

    EnderecoDTO endereco = fazerRequisicao(cep);

    if (endereco != null) {
      System.out.println(endereco);
    } else {
      System.out.println("Não foi possível encontrar o CEP ou houve um erro na requisição.");
    }

    scan.close();
  }

  // NOVO TIPO: O método agora retorna o objeto de domínio tipado (EnderecoDTO)
  public static EnderecoDTO lerJson(HttpResponse<String> response) throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    String json = response.body();
    // DESSERIALIZAÇÃO TIPADA: O Jackson mapeia diretamente o JSON para a classe
    // EnderecoDTO.
    EnderecoDTO endereco = mapper.readValue(json, EnderecoDTO.class);

    if (endereco.getCep() == null || endereco.getCep().isEmpty()) {
      System.out.println("CEP não encontrado.");
      return null;
    }

    return endereco;

  }

  // NOVO TIPO: Assinatura atualizada para retornar EnderecoDTO.
  public static EnderecoDTO fazerRequisicao(String cep) throws IOException, InterruptedException {
    String url = "https://viacep.com.br/ws/" + cep + "/json/";
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(url))
        .build();
    HttpResponse<String> response = client
        .send(request, HttpResponse.BodyHandlers.ofString());
    if (response.statusCode() != 200) {
      System.out.println("Erro na requisição HTTP: Status " + response.statusCode());
      return null;
    }

    return lerJson(response);
  }
}
