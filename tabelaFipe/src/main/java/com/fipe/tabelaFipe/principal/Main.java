package com.fipe.tabelaFipe.principal;

import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fipe.tabelaFipe.model.Dados;
import com.fipe.tabelaFipe.service.ConsumoApi;
import com.fipe.tabelaFipe.service.ConverteDados;

public class Main {
  private Scanner scan = new Scanner(System.in);
  private final String URL_BASE = "https://parallelum.com.br/fipe/api/v1/";
  private ConsumoApi consumo = new ConsumoApi();
  private ConverteDados conversor = new ConverteDados();

  public void exibirMenu() {
    var menu = """
        *** Opções***
        Carro
        Moto
        Caminhão

        Digite uma das opções para consultar:
        """;

    System.out.println(menu);
    var opcao = scan.nextLine();
    String endereco;

    if (opcao.toLowerCase().contains("carr")) {
      endereco = URL_BASE + "carros/marcas";
    } else if (opcao.toLowerCase().contains("mot")) {
      endereco = URL_BASE + "motos/marcas";
    } else if (opcao.toLowerCase().contains("cami")) {
      endereco = URL_BASE + "caminhoes/marcas";
    } else {
      System.out.println("Opção inválida");
      return;
    }

    var json = consumo.obterDados(endereco);

    List<Dados> marcas = conversor.obterLista(json, new TypeReference<List<Dados>>() {
    });

    marcas.forEach(System.out::println);

  }
}
