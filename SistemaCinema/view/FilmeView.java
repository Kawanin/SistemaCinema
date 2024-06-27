package view;

import java.util.Scanner;

public class FilmeView {
    private Scanner scanner;

    public FilmeView() {
        scanner = new Scanner(System.in);
    }

    public String lerTitulo() {
        System.out.print("> Título do filme: ");
        return scanner.nextLine();
    }

    public String lerGenero() {
        System.out.print("> Gênero do filme: ");
        return scanner.nextLine();
    }

    public int lerDuracao() {
        System.out.print("> Duração do filme (em minutos): ");
        return scanner.nextInt();
    }
}
