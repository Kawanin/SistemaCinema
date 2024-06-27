import model.*;
import view.*;

public class SalaController {
    private SalaView view;

    public SalaController(SalaView view) {
        this.view = view;
    }

    public Sala criarSala() {
        int numero = view.lerNumeroSala();
        int quantidadeAssentos = view.lerQuantidadeAssentos();
        return new Sala(numero, quantidadeAssentos);
    }
}
