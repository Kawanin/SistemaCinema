package view;

import java.util.Scanner;

public class SalaView {
    private Scanner scanner;

    public SalaView() {
        scanner = new Scanner(System.in);
    }

    public int lerNumeroSala() {
        System.out.print("> Número da sala: ");
        return scanner.nextInt();
    }

    public int lerQuantidadeAssentos() {
        System.out.print("> Quantidade de assentos: ");
        return scanner.nextInt();
    }
}
