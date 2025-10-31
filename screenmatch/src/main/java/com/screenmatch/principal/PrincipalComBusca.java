package com.screenmatch.principal;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.screenmatch.modelos.Titulo;
import com.screenmatch.modelos.TituloOmdb;

public class PrincipalComBusca {
  public static void main(String[] args) throws IOException, InterruptedException {

    Scanner scan = new Scanner(System.in);
    System.out.println("Digite o nome do filme para busca: ");
    String tituloDoFilme = scan.nextLine();

    // Busca a chave API da variável de ambiente
    String apiKey = System.getenv("OMDB_API_KEY");
    if (apiKey == null || apiKey.isEmpty()) {
      System.err.println("ERRO: Defina a variável de ambiente OMDB_API_KEY");
      System.exit(1);
    }

    String endereco = "https://www.omdbapi.com/?t=" + tituloDoFilme + "&apikey=" + apiKey;

    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(endereco))
        .build();
    HttpResponse<String> response = client
        .send(request, HttpResponse.BodyHandlers.ofString());

    String json = response.body();
    /* System.out.println(json); */

    Gson gson = new GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
        .create();

    TituloOmdb meuTituloOmdb = gson.fromJson(json, TituloOmdb.class);

    Titulo meuTitulo = new Titulo(meuTituloOmdb);
    System.out.println(meuTitulo);

    scan.close();
  }

}
