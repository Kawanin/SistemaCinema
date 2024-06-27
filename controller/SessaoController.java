package controller;

import java.sql.Date;

import model.Filme;
import model.Sala;
import model.Sessao;
import view.SessaoView;

public class SessaoController {
    private SessaoView view;
    private FilmeController filmeController;
    private SalaController salaController;
    public SessaoController(SessaoView view, FilmeController filmeController, SalaController salaController) {
        this.view = view;
        this.filmeController = filmeController;
        this.salaController = salaController;
    }

    public Sessao criarSessao() {
        Filme filme = filmeController.criarFilme();
        Sala sala = salaController.criarSala();
        Date horario = (Date) view.lerHorario();
        return new Sessao(filme, sala, horario);
    }
}
