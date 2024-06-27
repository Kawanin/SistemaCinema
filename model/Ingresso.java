package model;

public class Ingresso {
    private Cliente cliente;
    private Sessao sessao;
    private Assento assento;

    public Ingresso(Cliente cliente, Sessao sessao, Assento assento) {
        this.cliente = cliente;
        this.sessao = sessao;
        this.assento = assento;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Sessao getSessao() {
        return sessao;
    }

    public Assento getAssento() {
        return assento;
    }
}
