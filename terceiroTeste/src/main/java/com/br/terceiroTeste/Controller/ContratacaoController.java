package com.br.terceiroTeste.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.terceiroTeste.Candidato;
import com.br.terceiroTeste.ProcessoContratacao;

@RestController
@RequestMapping("/api/v1/hiring")
public class ContratacaoController {

  @GetMapping("/api/hello")
  public String hello(){
    return "hello word!!!!";
  }
  
  private ProcessoContratacao processoContratacao;

  public  ContratacaoController(){
    this.processoContratacao = new ProcessoContratacao();
  }

  @PostMapping("/start")
  public ResponseEntity<String> iniciarProcesso(@RequestBody CandidatoRequest request){
    String nome = request.getNome();
    if(nome == null || nome.isEmpty()){
      return ResponseEntity.badRequest().body("Nome do candidato não pode estar vazio.");
    }
    int codCandidato = processoContratacao.iniciarProcesso(nome);
    return ResponseEntity.ok("ID do candidato: " + codCandidato);
  }
 
  @PostMapping("/schedule")
  public ResponseEntity<String> marcarEntrevista(@RequestBody CandidatoCodeRequest request){
    int codCandidato = request.getCodCandidato();
    Candidato candidato = processoContratacao.findCandidateById(codCandidato);
    if(candidato == null){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Candidato não encontrado.");
    }
    processoContratacao.marcarEntrevista(codCandidato);
    return ResponseEntity.ok("Entrevista marcada para "+ codCandidato);
  }

  @PostMapping("/disqualify")
  public ResponseEntity<String> desqualificarCandidato(@RequestBody CandidatoCodeRequest request) {
      int codCandidato = request.getCodCandidato();
      Candidato candidate = processoContratacao.findCandidateById(codCandidato);
      if (candidate == null) {
          return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Candidato não encontrado.");
      }
      processoContratacao.desqualificarCandidato(codCandidato);
      return ResponseEntity.ok("Candidato " + codCandidato + " desqualificado.");
  }

  @PostMapping("/approve")
  public ResponseEntity<String> aprovarCandidato(@RequestBody CandidatoCodeRequest request) {
      int codCandidato = request.getCodCandidato();
      Candidato candidate = processoContratacao.findCandidateById(codCandidato);
      if (candidate == null) {
          return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Candidato não encontrado.");
      }
      processoContratacao.aprovarCandidato(codCandidato);
      return ResponseEntity.ok("Candidato " + codCandidato + " aprovado.");
  }

  @GetMapping("/status/candidate/{codCandidato}")
  public ResponseEntity<String> verificarStatusCandidato(@PathVariable int codCandidato) {
      String status = processoContratacao.verificarStatusCandidato(codCandidato);
      if (status.equals("Candidato não encontrado")) {
          return ResponseEntity.status(HttpStatus.NOT_FOUND).body(status);
      }
      return ResponseEntity.ok(status);
  }

  @GetMapping("/approved")
  public ResponseEntity<List<String>> obterAprovados() {
      List<String> approvedCandidates = processoContratacao.obterAprovados();
      return ResponseEntity.ok(approvedCandidates);
  }

  public class CandidatoRequest {
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}

public class CandidatoCodeRequest {
    private int codCandidato;

    public int getCodCandidato() {
        return codCandidato;
    }

    public void setCodCandidato(int codCandidato) {
        this.codCandidato = codCandidato;
    }
}

}
