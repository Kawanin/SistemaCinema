import model.*;
import view.*;

public class FilmeController {
    private FilmeView view;

    public FilmeController(FilmeView view) {
        this.view = view;
    }

    public Filme criarFilme() {
        String titulo = view.lerTitulo();
        String genero = view.lerGenero();
        int duracao = view.lerDuracao();
        return new Filme(titulo, genero, duracao);
    }
}
