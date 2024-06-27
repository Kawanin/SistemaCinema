package model;

import java.util.Date;

public class Sessao {
    private Filme filme;
    private Sala sala;
    private Date horario;

    public Sessao(Filme filme, Sala sala, Date horario) {
        this.filme = filme;
        this.sala = sala;
        this.horario = horario;
    }

    public Filme getFilme() {
        return filme;
    }

    public Sala getSala() {
        return sala;
    }

    public Date getHorario() {
        return horario;
    }
}
