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

    try {
      EnderecoDTO endereco = fazerRequisicao(cep);
      System.out.println(endereco);
    } catch (CepNotFoundException e) {
      System.out.println("Erro de busca, o CEP " + cep + " não foi encontrado.");
      System.out.println("Por gentileza, verifique o CEP e tente novamente.");
    } catch (IOException e) {
      if (e.getMessage().contains("Status 400")) {
        System.out.println("Erro de formato,  o CEP " + cep + "é inválido.");
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

  // NOVO TIPO: O método agora retorna o objeto de domínio tipado (EnderecoDTO)
  public static EnderecoDTO lerJson(HttpResponse<String> response) throws IOException, CepNotFoundException {
    ObjectMapper mapper = new ObjectMapper();
    String json = response.body();
    // DESSERIALIZAÇÃO TIPADA: O Jackson mapeia diretamente o JSON para a classe
    // EnderecoDTO.
    EnderecoDTO endereco = mapper.readValue(json, EnderecoDTO.class);

    if (endereco.isErro()) {
      throw new CepNotFoundException();
    }

    return endereco;

  }

  // NOVO TIPO: Assinatura atualizada para retornar EnderecoDTO.
  public static EnderecoDTO fazerRequisicao(String cep) throws IOException, InterruptedException, CepNotFoundException {
    String url = "https://viacep.com.br/ws/" + cep + "/json/";
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(url))
        .build();
    HttpResponse<String> response = client
        .send(request, HttpResponse.BodyHandlers.ofString());
    if (response.statusCode() != 200) {
      throw new IOException("Erro na requisição HTTP: Status " + response.statusCode());
    }

    return lerJson(response);
  }
}
