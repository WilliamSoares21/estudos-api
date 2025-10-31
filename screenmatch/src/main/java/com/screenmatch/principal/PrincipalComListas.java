package com.screenmatch.principal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

import com.screenmatch.modelos.Filme;
import com.screenmatch.modelos.Serie;
import com.screenmatch.modelos.Titulo;

public class PrincipalComListas {
  public static void main(String[] args) {
    // 1. Criação e Configuração dos Títulos
    // Adicionando duração e avaliações para o toString ser completo
    Filme meuFilme = new Filme("O Poderoso chefão", 1970, 175, "Francis Ford Coppola");
    meuFilme.avalia(9.5);
    meuFilme.avalia(9.0);

    Filme outroFilme = new Filme("Avatar", 2023, 192);
    outroFilme.avalia(7.8);
    outroFilme.avalia(8.5);

    var filmeTeste = new Filme("Dogville", 2003, 178, "Lars von Trier");
    filmeTeste.avalia(8.0);
    filmeTeste.avalia(9.0);

    // Construtor completo para a série
    Serie lost = new Serie("Lost", 2000, 6, 20, 42);

    ArrayList<Titulo> lista = new ArrayList<>();
    lista.add(meuFilme);
    lista.add(filmeTeste);
    lista.add(outroFilme);
    lista.add(lost);

    // --- Saída no Terminal Melhorada ---

    System.out.println("\n#############################################");
    System.out.println("## LISTA DE TÍTULOS (COM TOSTRING)         ##");
    System.out.println("#############################################");

    // O loop usa o método toString() que criamos (o mais conveniente para cada
    // tipo)
    for (Titulo item : lista) {
      System.out.println(item.toString());
    }

    System.out.println("\n---------------------------------------------\n");

    // ... (restante do código para Busca por Artistas e Lista Ordenada)

    // Apresentação da Lista de Títulos Ordenados
    System.out.println("#############################################");
    System.out.println("## LISTA DE TÍTULOS ORDENADOS (Nome)       ##");
    System.out.println("#############################################");

    Collections.sort(lista);

    for (Titulo item : lista) {
      // Agora, a chamada simples ao objeto já usa o toString() completo e organizado
      System.out.println(item);
    }

    System.out.println("---------------------------------------------\n");

    lista.sort(Comparator.comparing(Titulo::getAnoDeLancamento));

    System.out.println("#############################################");
    System.out.println("## LISTA DE TÍTULOS ORDENADOS (Ano)        ##");
    System.out.println("#############################################");
    System.out.println();
    System.out.printf("%-30s %s%n", "Título", "Ano de Lançamento");
    for (Titulo titulo : lista) {
      System.out.printf("%-30s %d%n", titulo.getNome(), titulo.getAnoDeLancamento());
    }

    /*
     * Abordagem alternativa e funcional usando Streams para obter o mesmo
     * resultado.
     *
     * Enquanto o loop 'for' acima é uma abordagem "imperativa" (dizendo ao
     * computador
     * CADA PASSO a ser feito), a abordagem com Streams é "declarativa" (descrevendo
     * O QUE queremos como resultado final).
     */
    // System.out.println(lista.stream()
    // .map(t -> String.format("%-30s %d", t.getNome(), t.getAnoDeLancamento()))
    // .reduce((t1, t2) -> t1 + "\n" + t2)
    // .orElse("Nenhum título disponível."));
    System.out.println("---------------------------------------------");
  }
}
