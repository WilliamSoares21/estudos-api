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
    return "Users [login=" + login + ", url=" + url + ", name=" + name + ", bio=" + bio + ", dataCriacao=" + dataCriacao
        + "]";
  }

}
