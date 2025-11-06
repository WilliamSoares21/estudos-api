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

  public static Users fazerRequisicao(String user) throws IOException, InterruptedException {
    String url = "https://api.github.com/users/" + user;
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(url))
        .build();
    HttpResponse<String> response = client
        .send(request, HttpResponse.BodyHandlers.ofString());
    return lerJson(response);
  }
}
