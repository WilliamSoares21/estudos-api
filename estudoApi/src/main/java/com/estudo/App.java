package com.estudo;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import com.google.gson.Gson;

public class App {
  public static void main(String[] args) throws IOException, InterruptedException {
    Scanner scan = new Scanner(System.in);

    System.out.println("OBS: Para resultados mais precisos, utilize palavras-chave em inglês.");
    System.out.println("\nDigite o nome do livro para busca: ");
    String tituloDoLivro = scan.nextLine();

    String tituloCodificado = URLEncoder.encode(tituloDoLivro, StandardCharsets.UTF_8.toString());

    String endereco = "https://www.googleapis.com/books/v1/volumes?q=" + tituloCodificado;

    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(endereco))
        .build();
    HttpResponse<String> response = client
        .send(request, HttpResponse.BodyHandlers.ofString());

    Gson gson = new Gson();

    // Faz o parsing: Converte a string JSON no objeto Java BooksApiResponse
    BooksApiResponse apiResponse = gson.fromJson(response.body(), BooksApiResponse.class);

    System.out.println("\n--- RESULTADO DA BUSCA ---");
    System.out.println("Total de itens encontrados: " + apiResponse.getTotalItems());

    if (apiResponse.getTotalItems() > 0 && apiResponse.getItems() != null) {
      for (Item item : apiResponse.getItems()) {
        VolumeInfo info = item.getVolumeInfo();

        // Tratamento simples só para garantir que o autor não é nulo
        String autores = (info.getAuthors() != null)
            ? String.join(", ", info.getAuthors())
            : "N/A";

        System.out.println("-------------------------------------");
        System.out.println("Título: " + info.getTitle());
        System.out.println("Autor(es): " + autores);
      }
    } else {
      System.out.println("Nenhum livro encontrado com o título: " + tituloDoLivro);

    }
    scan.close();

  }
}
