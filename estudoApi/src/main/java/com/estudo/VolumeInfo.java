package com.estudo;

// Representa as informações do volume retornadas pela API do Google Books
public class VolumeInfo {
  private String title;
  private java.util.List<String> authors;
  // O Gson preenche os campos automaticamente se os nomes coincidirem.

  public String getTitle() {
    return title;
  }

  public java.util.List<String> getAuthors() {
    return authors;
  }
}
