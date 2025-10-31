package com.screenmatch.principal;

import java.util.ArrayList;

import com.screenmatch.calculos.CalculadoraDeTempo;
import com.screenmatch.calculos.FiltroRecomendacao;
import com.screenmatch.modelos.Episodio;
import com.screenmatch.modelos.Filme;
import com.screenmatch.modelos.Serie;

public class App {
  public static void main(String[] args) {
    Filme meuFilme = new Filme("O poderoso chefão", 1970, 180);
    System.out.println("Duração do filme: " + meuFilme.getDuracaoEmMinutos());

    meuFilme.exibeFichaTecnica();
    meuFilme.avalia(8);
    meuFilme.avalia(5);
    meuFilme.avalia(10);
    System.out.println("Total de avaliações: " + meuFilme.getTotalDeAvaliacoes());
    System.out.printf("A média do filme: %.2f%n", meuFilme.pegaMedia());
    // meuFilme.somaDasAvaliacoes = 10;
    // meuFilme.totalDeAvaliacoes = 1;
    // System.out.println(meuFilme.pegaMedia());

    Serie lost = new Serie("Lost", 2000, 10, 10, 50);
    lost.exibeFichaTecnica();
    System.out.println("Duração para maratonar Lost: " + lost.getDuracaoEmMinutos());

    Filme outroFilme = new Filme("Avatar", 2023, 200);

    CalculadoraDeTempo calculadora = new CalculadoraDeTempo();
    calculadora.inclui(meuFilme);
    calculadora.inclui(outroFilme);
    calculadora.inclui(lost);
    System.out.println(calculadora.getTempoTotal());

    FiltroRecomendacao filtro = new FiltroRecomendacao();
    filtro.filtra(meuFilme);

    Episodio episodio = new Episodio(1, lost);
    episodio.setTotalVisualizacoes(300);
    filtro.filtra(episodio);

    Filme filmeTeste = new Filme("US", 2019, 200);
    filmeTeste.avalia(7);

    ArrayList<Filme> listaDeFilmes = new ArrayList<>();
    listaDeFilmes.add(filmeTeste);
    listaDeFilmes.add(meuFilme);
    listaDeFilmes.add(outroFilme);

    System.out.println("Tamanho da lista " + listaDeFilmes.size());
    for (int i = 0; i < listaDeFilmes.size(); i++) {
      System.out.println((i + 1) + "ª Adicionado - " + listaDeFilmes.get(i));
    }

  }
}
