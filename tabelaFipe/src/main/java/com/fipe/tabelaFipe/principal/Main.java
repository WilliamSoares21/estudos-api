package com.fipe.tabelaFipe.principal;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fipe.tabelaFipe.model.Dados;
import com.fipe.tabelaFipe.model.Modelos;
import com.fipe.tabelaFipe.model.Veiculo;
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

    System.out.println("Digite o código da marca desejada:");
    var codigoMarca = scan.nextLine();

    endereco = endereco + "/" + codigoMarca + "/modelos";
    json = consumo.obterDados(endereco);

    var modeloLista = conversor.obterDados(json, Modelos.class);

    System.out.println("\nModelos disponíveis:");
    modeloLista.modelos().stream()
        .sorted(Comparator.comparing(Dados::codigo))
        .forEach(System.out::println);

    System.out.println("Digite um trecho do nome do modelo desejado:");
    var nomeVeiculo = scan.nextLine();

    List<Dados> modelosFiltrados = modeloLista.modelos().stream()
        .filter(m -> m.nome().toLowerCase().contains(nomeVeiculo.toLowerCase()))
        .collect(Collectors.toList());

    System.out.println("\nModelos Filtrados:");
    modelosFiltrados.forEach(System.out::println);

    System.out.println("Digite por favor o codigo do modelo para buscar os valores da avaliação:");
    var codigoModelo = scan.nextLine();

    endereco = endereco + "/" + codigoModelo + "/anos";
    json = consumo.obterDados(endereco);
    List<Dados> anos = conversor.obterLista(json, new TypeReference<List<Dados>>() {
    });

    List<Veiculo> veiculos = new ArrayList<>();

    for (int i = 0; i < anos.size(); i++) {
      var enderecoAnos = endereco + "/" + anos.get(i).codigo();
      json = consumo.obterDados(enderecoAnos);
      Veiculo veiculo = conversor.obterDados(json, Veiculo.class);
      veiculos.add(veiculo);
    }

    System.out.println("\nValores de avaliação:");
    veiculos.forEach(v -> {
      System.out.println("Ano: " + v.anoModelo() + " | Combustível: " + v.combustivel() + " | Valor: " + v.valor());
    });

  }
}
