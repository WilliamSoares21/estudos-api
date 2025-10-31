package com.screenmatch.modelos;

import com.screenmatch.calculos.Classificavel;

public class Filme extends Titulo implements Classificavel {
  private String diretor;

  // Construtor padrão
  public Filme() {
    super();
  }

  // Construtor com parâmetros básicos
  public Filme(String nome, int anoDeLancamento) {
    super(nome, anoDeLancamento);
  }

  // Construtor completo
  public Filme(String nome, int anoDeLancamento, int duracaoEmMinutos) {
    super(nome, anoDeLancamento, duracaoEmMinutos);
  }

  // Construtor com diretor
  public Filme(String nome, int anoDeLancamento, int duracaoEmMinutos, String diretor) {
    super(nome, anoDeLancamento, duracaoEmMinutos);
    this.diretor = diretor;
  }

  public String getDiretor() {
    return diretor;
  }

  public void setDiretor(String diretor) {
    this.diretor = diretor;
  }

  @Override
  public int getClassificacao() {
    // Retorna a média arredondada, dividida por 2, para simular estrelas (0 a 5)
    return (int) pegaMedia() / 2;
  }

  @Override
  public String toString() {
    // 1. Usa a representação da superclasse (Título e Ano)
    // 2. Adiciona detalhes específicos de Filme (Diretor, Duração, Classificação)
    return super.toString() +
        String.format(" | Dir: %s | Duração: %d min | Nota Média: %.1f | Classificação: %d/5",
            diretor == null ? "N/A" : diretor,
            getDuracaoEmMinutos(),
            pegaMedia(),
            getClassificacao());
  }
}
