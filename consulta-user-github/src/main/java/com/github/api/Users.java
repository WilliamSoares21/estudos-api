package com.github.api;

public class Users {
  private String login;
  private String url;
  private String name;
  private String bio;
  private String dataCriacao;

  public Users() {
  }

  public Users(String login, String url, String name, String bio, String dataCriacao) {
    this.login = login;
    this.url = url;
    this.name = name;
    this.bio = bio;
    this.dataCriacao = dataCriacao;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getBio() {
    return bio;
  }

  public void setBio(String bio) {
    this.bio = bio;
  }

  public String getDataCriacao() {
    return dataCriacao;
  }

  public void setDataCriacao(String dataCriacao) {
    this.dataCriacao = dataCriacao;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("\n=== GitHub User ===\n");
    sb.append(String.format("%-12s: %s\n", "Login", login));
    sb.append(String.format("%-12s: %s\n", "URL", url));
    sb.append(String.format("%-12s: %s\n", "Name", name != null ? name : "-"));
    sb.append(String.format("%-12s: %s\n", "Created At", dataCriacao != null ? dataCriacao : "-"));
    sb.append(String.format("%-12s: ", "Bio"));

    if (bio != null && !bio.isBlank()) {
      int wrap = 60;
      for (int i = 0; i < bio.length(); i += wrap) {
        int end = Math.min(i + wrap, bio.length());
        sb.append(bio, i, end).append("\n");
        if (end < bio.length())
          sb.append(" ".repeat(15));
      }
    } else {
      sb.append("-\n");
    }
    return sb.toString();
  }

}
