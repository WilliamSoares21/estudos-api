package com.books;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class BooksService {

  public static VolumeInfo lerJson(HttpResponse<String> response) throws IOException {
    Gson gson = new GsonBuilder().create();

    GoogleBooksResponse responseData = gson.fromJson(response.body(), GoogleBooksResponse.class);

    if (responseData.getItems() != null && !responseData.getItems().isEmpty()) {
      BookItem firBookItem = responseData.getItems().get(0);
      return firBookItem.getVolumeInfo();
    } else {
      throw new IOException("Nenhum livro encontrado");
    }
  }

  public static VolumeInfo fazerRequisicao(String livro) throws IOException, InterruptedException {
    String nomeLivroFormatado = livro.replace(" ", "+");
    String url = "https://www.googleapis.com/books/v1/volumes?q=" + nomeLivroFormatado;
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(url))
        .build();
    HttpResponse<String> response = client
        .send(request, HttpResponse.BodyHandlers.ofString());
    return lerJson(response);
  }
}
