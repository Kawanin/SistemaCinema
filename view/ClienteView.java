package view;

import java.util.Scanner;

public class ClienteView {
    private Scanner scanner;

    public ClienteView() {
        scanner = new Scanner(System.in);
    }

    public String lerNome() {
        System.out.print("Nome do cliente: ");
        return scanner.nextLine();
    }

    public String lerCpf() {
        System.out.print("CPF do cliente: ");
        return scanner.nextLine();
    }
}
