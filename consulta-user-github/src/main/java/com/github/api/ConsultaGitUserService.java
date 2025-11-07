package com.github.api;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ConsultaGitUserService {

  public static Users lerJson(HttpResponse<String> response) {
    Gson gson = new GsonBuilder().create();

    Users usersData = gson.fromJson(response.body(), Users.class);

    return usersData;
  }

  public static Users fazerRequisicao(String user) {
    String url = "https://api.github.com/users/" + user;
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(url))
        .build();

    HttpResponse<String> response;

    try {
      response = client.send(request, HttpResponse.BodyHandlers.ofString());
    } catch (IOException e) {
      throw new ErroRequisicaoException("Falha de conexão ou leitura de dados: " + e.getMessage());
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      throw new ErroRequisicaoException("A requisição foi interrompida: " + e.getMessage());
    }

    int statusCode = response.statusCode();

    if (statusCode == 404) {
      throw new ErroConsultaGitHubException("Usuário " + user + " não foi encontrado no GitHub");
    } else if (statusCode >= 400) {
      String erroDetalhe = response.body().contains("message") ? response.body() : "Detalhe não fornecido pela API.";
      throw new ErroRequisicaoException("Erro na API (Status " + statusCode + "). " + erroDetalhe);
    }
    return lerJson(response);
  }
}
