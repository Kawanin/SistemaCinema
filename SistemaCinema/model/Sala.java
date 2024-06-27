package model;

import java.util.ArrayList;
import java.util.List;

public class Sala {
    private int numero;
    private List<Assento> assentos;

    public Sala(int numero, int quantidadeAssentos) {
        this.numero = numero;
        this.assentos = new ArrayList<>();
        for (int i = 1; i <= quantidadeAssentos; i++) {
            this.assentos.add(new Assento(i));
        }
    }

    public int getNumero() {
        return numero;
    }

    public List<Assento> getAssentos() {
        return assentos;
    }
}
