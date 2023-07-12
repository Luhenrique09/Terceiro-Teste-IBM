package com.br.terceiroTeste;

import java.util.ArrayList;
import java.util.List;

public class ProcessoContratacao {
  private List<Candidato> candidatos;
  private List<Candidato> candidatosAprovados;

  public ProcessoContratacao(){
    this.candidatos = new ArrayList<>();
    this.candidatosAprovados = new ArrayList<>();
  }

  public int iniciarProcesso(String nome){
    Candidato candidato = new Candidato(nome);
    candidatos.add(candidato);
    return candidato.getId();
  }

  public void marcarEntrevista(int codCandidato){
    Candidato candidato = findCandidateById(codCandidato);
    if(candidato != null){
      candidato.setStatus("Qualificado");
    } 
  }

  public void desqualificarCandidato(int codCandidato){
    Candidato candidato = findCandidateById(codCandidato);
    if(candidato != null){
      candidatos.remove(candidato);
    }
  }

  public String verificarStatusCandidato(int codCandidato){
    Candidato candidato = findCandidateById(codCandidato);
    if(candidato != null){
      return candidato.getStatus();
    }else {
      return "Candidato não encontrado";
    }
  }

  public void aprovarCandidato(int codCandidato){
    Candidato candidato = findCandidateById(codCandidato);
    if(candidato != null){
      candidato.setStatus("Aprovado");
      candidatosAprovados.add(candidato);
    }
  }

  public List<String> obterAprovados(){
    List<String> nomesAprovados = new ArrayList<>();
    for(Candidato candidato : candidatosAprovados){
      nomesAprovados.add(candidato.getNome());
    }
    return nomesAprovados;
  }


  public Candidato findCandidateById(int codCandidato) {
    for (Candidato candidato : candidatos) {
        if (candidato.getId() == codCandidato) {
            return candidato;
        }
    }
    return null;
}
}
