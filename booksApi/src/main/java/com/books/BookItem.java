package com.books;

public class BookItem {
  private String id;
  private VolumeInfo volumeInfo;

  public BookItem(String id, VolumeInfo volumeInfo) {
    this.id = id;
    this.volumeInfo = volumeInfo;
  }

  public String getId() {
    return id;
  }

  public VolumeInfo getVolumeInfo() {
    return volumeInfo;
  }

  @Override
  public String toString() {
    return "BookItem{" +
        "id='" + id + '\'' +
        ", volumeInfo=" + volumeInfo +
        '}';
  }
}
