package controller;

import model.Cliente;
import view.ClienteView;

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
