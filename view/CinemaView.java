package view;

import java.util.List;
import java.util.Scanner;

import controller.Assento;
import model.Sessao;

public class CinemaView {
    private Scanner scanner;

    public CinemaView() {
        scanner = new Scanner(System.in);
    }

    public void exibirMenu() {
        System.out.println("1. Adicionar Filme");
        System.out.println("2. Adicionar Sala");
        System.out.println("3. Adicionar Sessao");
        System.out.println("4. Comprar Ingresso");
        System.out.println("5. Exibir Mapa de Assentos");
        System.out.println("0. Sair");
    }

    public int lerOpcao() {
        System.out.print("Escolha uma opção: ");
        return scanner.nextInt();
    }

    public String lerString(String mensagem) {
        System.out.print(mensagem);
        return scanner.next();
    }

    public int lerInt(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextInt();
    }

    public void mostrarSessoes(List<Sessao> sessoes) {
        for (int i = 0; i < sessoes.size(); i++) {
            Sessao sessao = sessoes.get(i);
            System.out.println(i + ". " + sessao.getFilme().getTitulo() + " - " + sessao.getHorario());
        }
    }

    public void mostrarAssentos(List<Assento> assentos) {
        for (Assento assento : assentos) {
            System.out.print(assento.getNumero() + (assento.isOcupado() ? "[X]" : "[O]") + " ");
        }
        System.out.println();
    }
}