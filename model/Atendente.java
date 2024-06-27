package model;

public class Atendente extends Funcionario {
    public Atendente(String nome, String cpf) {
        super(nome, cpf);
    }

    @Override
    public void realizarTarefa() {
        System.out.println("Atendente realizando tarefa.");
    }
}
