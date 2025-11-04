package com.books;

import java.util.List;

public class VolumeInfo {
  private String title;
  private List<String> authors;
  private String publisher;
  private String publishedDate;
  private String description;

  public VolumeInfo() {
  }

  public VolumeInfo(String title, List<String> authors, String publisher, String publishedDate, String description) {
    this.title = title;
    this.authors = authors;
    this.publisher = publisher;
    this.publishedDate = publishedDate;
    this.description = description;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public List<String> getAuthors() {
    return authors;
  }

  public void setAuthors(List<String> authors) {
    this.authors = authors;
  }

  public String getPublisher() {
    return publisher;
  }

  public void setPublisher(String publisher) {
    this.publisher = publisher;
  }

  public String getPublishedDate() {
    return publishedDate;
  }

  public void setPublishedDate(String publishedDate) {
    this.publishedDate = publishedDate;
  }

  public String getDescription() {
    return description;
  }

  @Override
  public String toString() {
    return "\n--- Detalhes do Livro ---\n" +
        "Título: " + title + "\n" +
        "Autor(es): " + (authors != null ? String.join(", ", authors) : "N/A") + "\n" +
        "Editora: " + (publisher != null ? publisher : "N/A") + "\n" +
        "Data de Publicação: " + (publishedDate != null ? publishedDate : "N/A") + "\n" +
        "Descrição (Resumo): "
        + (description != null ? description.substring(0, Math.min(description.length(), 150)) + "..." : "N/A") + "\n" +
        "--------------------------";
  }
}
