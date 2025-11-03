package com.books;

import com.google.gson.Gson;

public class App {
  public static void main(String[] args) {
    Pessoa pessoa = new Pessoa("Ana", 30, "São Paulo");

    Gson gson = new Gson();

    Pessoa p2 = gson.fromJson("{\"nome\":\"Carlos\",\"idade\":25,\"cidade\":\"Rio de Janeiro\"}", Pessoa.class);

    System.out.println("Objeto Pessoa: " + p2);
    System.out.println("Nome: " + p2.nome());
    System.out.println("Idade: " + p2.idade());

    System.out.println(pessoa.nome());
    System.out.println(pessoa.idade());
    System.out.println(pessoa.cidade());
  }
}
