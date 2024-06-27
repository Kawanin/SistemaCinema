import model.*;
import view.*;

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
        // filme.exibirFilmes(); <------------- adicionar
        Sala sala = salaController.criarSala();
        // sala.exibirSalas(); <--------------- adicionar

        String horario = view.lerHorario();
        return new Sessao(filme, sala, horario);
    }
}
