package com.cep;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonFileWriter {
  public static void gravarEnderecoEmJson(EnderecoDTO endereco) throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    File enderecoJson = new File("endereco_" + endereco.getCep() + ".json");

    mapper.writeValue(enderecoJson, endereco);

    System.out.println("Dados gravados com sucesso em: " + enderecoJson.getAbsolutePath());
  }
}
