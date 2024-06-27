package model;



public class Sessao {
    private Filme filme;
    private Sala sala;
    private String horario;

    public Sessao(Filme filme, Sala sala, String horario) {
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

    public String getHorario() {
        return horario;
    }
}
