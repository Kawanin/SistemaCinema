package model;

public class Gerente extends Funcionario {
    public Gerente(String nome, String cpf) {
        super(nome, cpf);
    }

    @Override
    public void realizarTarefa() {
        System.out.println("Gerente realizando tarefa.");
    }
}
