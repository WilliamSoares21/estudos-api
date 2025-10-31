package com.screenmatch.modelos;

public class Serie extends Titulo {
  private int temporadas;
  private boolean ativa;
  private int episodiosPorTemporada;
  private int minutosPorEpisodio;

  // Construtor padrão
  public Serie() {
    super();
  }

  // Construtor com parâmetros básicos
  public Serie(String nome, int anoDeLancamento) {
    super(nome, anoDeLancamento);
  }

  // Construtor completo
  public Serie(String nome, int anoDeLancamento, int temporadas, int episodiosPorTemporada, int minutosPorEpisodio) {
    super(nome, anoDeLancamento);
    this.temporadas = temporadas;
    this.episodiosPorTemporada = episodiosPorTemporada;
    this.minutosPorEpisodio = minutosPorEpisodio;
  }

  public int getTemporadas() {
    return temporadas;
  }

  public void setTemporadas(int temporadas) {
    this.temporadas = temporadas;
  }

  public boolean isAtiva() {
    return ativa;
  }

  public void setAtiva(boolean ativa) {
    this.ativa = ativa;
  }

  public int getEpisodiosPorTemporada() {
    return episodiosPorTemporada;
  }

  public void setEpisodiosPorTemporada(int episodiosPorTemporada) {
    this.episodiosPorTemporada = episodiosPorTemporada;
  }

  public int getMinutosPorEpisodio() {
    return minutosPorEpisodio;
  }

  public void setMinutosPorEpisodio(int minutosPorEpisodio) {
    this.minutosPorEpisodio = minutosPorEpisodio;
  }

  @Override
  public int getDuracaoEmMinutos() {
    return temporadas * episodiosPorTemporada * minutosPorEpisodio;
  }

  @Override
  public String toString() {
    // 1. Usa a representação da superclasse (Título e Ano)
    // 2. Adiciona detalhes específicos de Série (Temporadas e Duração Total)
    return super.toString() +
        String.format(" | Temporadas: %d | Episódios: %d | Duração Total: %d minutos",
            temporadas,
            getEpisodiosPorTemporada(),
            getDuracaoEmMinutos());
  }
}
