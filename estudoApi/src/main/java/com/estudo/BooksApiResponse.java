package com.estudo;

// Representa a resposta completa da API (o objeto JSON raiz)
public class BooksApiResponse {
  private java.util.List<Item> items;
  private int totalItems;

  public java.util.List<Item> getItems() {
    return items;
  }

  public int getTotalItems() {
    return totalItems;
  }

}
