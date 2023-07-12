package com.br.terceiroTeste;

public class Candidato {
  private static int nextId = 1;

  private int id;
  private String nome;
  private String status;

  public Candidato(String nome){
    this.id = nextId++;
    this.nome = nome;
    this.status = "Recebido";
  }

  public int getId() {
    return id;
}

public String getNome() {
    return nome;
}

public String getStatus() {
    return status;
}

public void setStatus(String status) {
    this.status = status;
}
}
