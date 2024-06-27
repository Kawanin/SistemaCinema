package model;

import java.util.ArrayList;
import java.util.List;

public class Cinema {
    private List<Filme> filmes;
    private List<Sala> salas;
    private List<Sessao> sessoes;
    private List<Ingresso> ingressos;

    public Cinema() {
        this.filmes = new ArrayList<>();
        this.salas = new ArrayList<>();
        this.sessoes = new ArrayList<>();
        this.ingressos = new ArrayList<>();
    }

    public void adicionarFilme(Filme filme) {
        filmes.add(filme);
    }

    public void adicionarSala(Sala sala) {
        salas.add(sala);
    }

    public void adicionarSessao(Sessao sessao) {
        sessoes.add(sessao);
    }

    public void adicionarIngresso(Ingresso ingresso) {
        ingressos.add(ingresso);
    }

    public List<Filme> getFilmes() {
        return filmes;
    }

    public List<Sala> getSalas() {
        return salas;
    }

    public List<Sessao> getSessoes() {
        return sessoes;
    }

    public List<Ingresso> getIngressos() { // Adicione esta linha
        return ingressos;
    }
}
