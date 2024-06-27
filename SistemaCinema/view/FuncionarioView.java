package view;

import java.util.Scanner;

public class FuncionarioView {
    private Scanner scanner;

    public FuncionarioView() {
        scanner = new Scanner(System.in);
    }

    public String lerNome() {
        System.out.print("> Nome do funcionário: ");
        return scanner.nextLine();
    }

    public String lerCpf() {
        System.out.print("> CPF do funcionário: ");
        return scanner.nextLine();
    }
}