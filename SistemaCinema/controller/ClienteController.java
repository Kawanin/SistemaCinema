import model.*;
import view.*;

public class ClienteController {
    private ClienteView view;

    public ClienteController(ClienteView view) {
        this.view = view;
    }

    public Cliente criarCliente() {
        String nome = view.lerNome();
        String cpf = view.lerCpf();
        return new Cliente(nome, cpf);
    }
}
