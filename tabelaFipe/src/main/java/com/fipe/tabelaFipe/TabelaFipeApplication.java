package com.fipe.tabelaFipe;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fipe.tabelaFipe.principal.Main;

@SpringBootApplication
public class TabelaFipeApplication implements CommandLineRunner {

  public static void main(String[] args) {
    SpringApplication.run(TabelaFipeApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    Main Principal = new Main();
    Principal.exibirMenu();
  }
}
