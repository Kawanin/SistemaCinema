package view;

import java.util.Scanner;
import java.util.Date;

public class SessaoView {
    private Scanner scanner;

    public SessaoView() {
        scanner = new Scanner(System.in);
    }

    public Date lerHorario() {
        System.out.print("Horário da sessão (formato HH:MM): ");
        String horario = scanner.nextLine();
        return new Date(); // placeholder
    }
}
