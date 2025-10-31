package com.screenmatch.modelos;

import com.screenmatch.calculos.Classificavel;

public class Episodio implements Classificavel {
  private int numero;
  private String nome;
  private Serie serie;
  private int totalVisualizacoes;

  // Construtor padrão
  public Episodio() {
  }

  // Construtor com parâmetros básicos
  public Episodio(int numero, Serie serie) {
    this.numero = numero;
    this.serie = serie;
  }

  // Construtor completo
  public Episodio(int numero, String nome, Serie serie, int totalVisualizacoes) {
    this.numero = numero;
    this.nome = nome;
    this.serie = serie;
    this.totalVisualizacoes = totalVisualizacoes;
  }

  public int getTotalVisualizacoes() {
    return totalVisualizacoes;
  }

  public void setTotalVisualizacoes(int totalVisualizacoes) {
    this.totalVisualizacoes = totalVisualizacoes;
  }

  public int getNumero() {
    return numero;
  }

  public void setNumero(int numero) {
    this.numero = numero;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public Serie getSerie() {
    return serie;
  }

  public void setSerie(Serie serie) {
    this.serie = serie;
  }

  @Override
  public int getClassificacao() {
    if (totalVisualizacoes > 100) {
      return 4;
    } else {
      return 2;
    }
  }
}
