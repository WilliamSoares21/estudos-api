package com.books;

import java.util.List;

public class GoogleBooksResponse {
  private List<BookItem> items;

  public List<BookItem> getItems() {
    return items;
  }

  public void setItems(List<BookItem> items) {
    this.items = items;
  }
}
