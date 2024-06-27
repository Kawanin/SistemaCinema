import java.io.*;
import java.util.List;

import model.*;
import view.*;

public class CinemaController {
    private Cinema cinema;
    private CinemaView view;

    public CinemaController(Cinema cinema, CinemaView view) {
        this.cinema = cinema;
        this.view = view;
    }

    public void iniciar() {
        while (true) {
            view.exibirMenu();
            int opcao = view.lerOpcao();
            switch (opcao) {
                case 1:
                    adicionarFilme();
                    break;
                case 2:
                    adicionarSala();
                    break;
                case 3:
                    adicionarSessao();
                    break;
                case 4:
                    comprarIngresso();
                    break;
                case 5:
                    exibirMapaAssentos();
                    break;
                case 6:
                    adicionarFuncionario();
                    break;
                case 7:
                    exibirFuncionarios();
                    break;
                case 0:
                    salvarDados();
                    return;
                default:
                    System.out.println("> Opção inválida.");
            }
        }
    }
    
    private void adicionarFilme() {
        FilmeView filmeView = new FilmeView();
        FilmeController filmeController = new FilmeController(filmeView);
        Filme filme = filmeController.criarFilme();
        cinema.adicionarFilme(filme);
        System.out.println("> Filme adicionado com sucesso!");
    }

    private void adicionarSala() {
        SalaView salaView = new SalaView();
        SalaController salaController = new SalaController(salaView);
        Sala sala = salaController.criarSala();
        cinema.adicionarSala(sala);
        System.out.println("> Sala adicionada com sucesso!");
    }

    private void adicionarSessao() {
        SessaoView sessaoView = new SessaoView();
        FilmeView filmeView = new FilmeView();
        SalaView salaView = new SalaView();
        FilmeController filmeController = new FilmeController(filmeView);
        SalaController salaController = new SalaController(salaView);
        SessaoController sessaoController = new SessaoController(sessaoView, filmeController, salaController);
        Sessao sessao = sessaoController.criarSessao();
        cinema.adicionarSessao(sessao);
        System.out.println("> Sessão adicionada com sucesso!");
    }

    private void adicionarFuncionario(){
        FuncionarioView funcionarioView = new FuncionarioView();
        FuncionarioController funcionarioController = new FuncionarioController(funcionarioView);

        System.out.println("> Adicionar Novo Funcionario");
        String tipo = view.lerString("> Tipo de funcionario (Atendente/Gerente): \n");
        Funcionario funcionario = funcionarioController.criarFuncionario(tipo);
        if (funcionario != null) {
            System.out.println("> " + tipo + " Adicionado com sucesso!");
        }else{
            System.out.println("> Invalido");
        }
    }

    private void exibirFuncionarios() {
        System.out.println("> Lista de Funcionários:");
        for (Funcionario funcionario : cinema.getFuncionarios()) {
            funcionario.mostrarInformacoes();
            System.out.println("");
        }
    }

    private void comprarIngresso() {
        ClienteView clienteView = new ClienteView();
        ClienteController clienteController = new ClienteController(clienteView);
        Cliente cliente = clienteController.criarCliente();

        System.out.println("");
        exibirSessoes();
        int sessaoIndex = view.lerInt("> Escolha a sessão (índice): ");
        Sessao sessao = cinema.getSessoes().get(sessaoIndex);

        exibirAssentos(sessao);
        int assentoIndex = view.lerInt("> Escolha o assento (índice): ");
        Assento assento = sessao.getSala().getAssentos().get(assentoIndex);

        if (!assento.isOcupado()) {
            assento.setOcupado(true);
            Ingresso ingresso = new Ingresso(cliente, sessao, assento);
            cinema.adicionarIngresso(ingresso);
            System.out.println("> Ingresso comprado com sucesso!");
        } else {
            System.out.println("> Assento já ocupado.");
        }
    }

    private void exibirSessoes() {
        List<Sessao> sessoes = cinema.getSessoes();
        for (int i = 0; i < sessoes.size(); i++) {
            Sessao sessao = sessoes.get(i);
            System.out.println(i + ". " + sessao.getFilme().getTitulo() + " - " + sessao.getHorario());
        }
    }

    private void exibirAssentos(Sessao sessao) {
        List<Assento> assentos = sessao.getSala().getAssentos();
        for (int i = 0; i < assentos.size(); i++) {
            Assento assento = assentos.get(i);
            System.out.print(i + (assento.isOcupado() ? "[X]" : "[O]") + " ");
        }
        System.out.println();
    }

    private void exibirMapaAssentos() {
        for (Sessao sessao : cinema.getSessoes()) {
            System.out.println("> Sessão: " + sessao.getFilme().getTitulo() + " - " + sessao.getHorario());
            for (Assento assento : sessao.getSala().getAssentos()) {
                System.out.print(assento.isOcupado() ? "[X]" : "[O]");
            }
            System.out.println();
        }
    }

    private void salvarDados() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("dados.txt"))) {
            for (Filme filme : cinema.getFilmes()) {
                writer.write("> Filme: " + filme.getTitulo() + "\n> Gênero: " + filme.getGenero() + "\n Duração (em minutos): " + filme.getDuracao() + "\n");
            }
            for (Sala sala : cinema.getSalas()) {
                writer.write("> Sala: " + sala.getNumero() + "\n> Assentos:" + sala.getAssentos().size() + "\n");
            }
            for (Sessao sessao : cinema.getSessoes()) {
                writer.write("> Sessao: " + sessao.getFilme().getTitulo() + "\n> Sala: " + sessao.getSala().getNumero() + "\n> Horário: " + sessao.getHorario() + "\n");
                System.out.println();
            }
            for (Ingresso ingresso : cinema.getIngressos()) { 
                writer.write("> Ingresso: \n> CPF: " + ingresso.getCliente().getCpf() + "\n> Nome: "+ingresso.getCliente().getNome() +"\n> Filme: " + ingresso.getSessao().getFilme().getTitulo() + "\n> Sala: " + ingresso.getSessao().getSala().getNumero() + "\n> Assento: " + ingresso.getAssento().getNumero() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
