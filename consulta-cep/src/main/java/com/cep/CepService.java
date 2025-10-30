package com.cep;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

class CepService {

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
  // This class can be expanded in the future to include more functionalities
}
