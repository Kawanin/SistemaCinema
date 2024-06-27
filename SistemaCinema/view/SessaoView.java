package view;

import java.util.Scanner;

public class SessaoView {
    private Scanner scanner;

    public SessaoView() {
        scanner = new Scanner(System.in);
    }

    public String lerHorario() {
        System.out.print("> Horário da sessão (formato HH:MM): ");
        String horario = scanner.nextLine();
       
        return horario;
    }

}
