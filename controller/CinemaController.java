package controller;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import model.*;
import view.CinemaView;
import view.*;


public class CinemaController {
    private Cinema cinema;
    private CinemaView view;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    public CinemaController(Cinema cinema, CinemaView view) {
        this.cinema = cinema;
        this.view = view;
    }

    public void iniciar() {
        carregarDados();
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
                case 0:
                    salvarDados();
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private void adicionarFilme() {
        FilmeView filmeView = new FilmeView();
        FilmeController filmeController = new FilmeController(filmeView);
        Filme filme = filmeController.criarFilme();
        cinema.adicionarFilme(filme);
        System.out.println("Filme adicionado com sucesso!");
    }

    private void adicionarSala() {
        SalaView salaView = new SalaView();
        SalaController salaController = new SalaController(salaView);
        Sala sala = salaController.criarSala();
        cinema.adicionarSala(sala);
        System.out.println("Sala adicionada com sucesso!");
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
        System.out.println("Sessão adicionada com sucesso!");
    }

    private void comprarIngresso() {
        ClienteView clienteView = new ClienteView();
        ClienteController clienteController = new ClienteController(clienteView);
        Cliente cliente = clienteController.criarCliente();

        exibirSessoes();
        int sessaoIndex = view.lerInt("Escolha a sessão (índice): ");
        Sessao sessao = cinema.getSessoes().get(sessaoIndex);

        exibirAssentos(sessao);
        int assentoIndex = view.lerInt("Escolha o assento (índice): ");
        Assento assento = sessao.getSala().getAssentos().get(assentoIndex);

        if (!assento.isOcupado()) {
            assento.setOcupado(true);
            Ingresso ingresso = new Ingresso(cliente, sessao, assento);
            cinema.adicionarIngresso(ingresso);
            System.out.println("Ingresso comprado com sucesso!");
        } else {
            System.out.println("Assento já ocupado.");
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
            System.out.println("Sessão: " + sessao.getFilme().getTitulo() + " - " + sessao.getHorario());
            for (Assento assento : sessao.getSala().getAssentos()) {
                System.out.print(assento.isOcupado() ? "[X]" : "[O]");
            }
            System.out.println();
        }
    }

    private void salvarDados() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("dados.txt"))) {
            for (Filme filme : cinema.getFilmes()) {
                writer.write("Filme:" + filme.getTitulo() + "," + filme.getGenero() + "," + filme.getDuracao() + "\n");
            }
            for (Sala sala : cinema.getSalas()) {
                writer.write("Sala:" + sala.getNumero() + "," + sala.getAssentos().size() + "\n");
            }
            for (Sessao sessao : cinema.getSessoes()) {
                writer.write("Sessao:" + sessao.getFilme().getTitulo() + "," + sessao.getSala().getNumero() + "," + dateFormat.format(sessao.getHorario()) + "\n");
            }
            for (Ingresso ingresso : cinema.getIngressos()) {  // Adicione esta linha
                writer.write("Ingresso:" + ingresso.getCliente().getCpf() + "," + ingresso.getSessao().getFilme().getTitulo() + "," + ingresso.getSessao().getSala().getNumero() + "," + ingresso.getAssento().getNumero() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void carregarDados() {
        try (BufferedReader reader = new BufferedReader(new FileReader("dados.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                switch (parts[0]) {
                    case "Filme":
                        String[] filmeData = parts[1].split(",");
                        Filme filme = new Filme(filmeData[0], filmeData[1], Integer.parseInt(filmeData[2]));
                        cinema.adicionarFilme(filme);
                        break;
                    case "Sala":
                        String[] salaData = parts[1].split(",");
                        Sala sala = new Sala(Integer.parseInt(salaData[0]), Integer.parseInt(salaData[1]));
                        cinema.adicionarSala(sala);
                        break;
                    case "Sessao":
                        String[] sessaoData = parts[1].split(",");
                        Filme sessaoFilme = cinema.getFilmes().stream().filter(f -> f.getTitulo().equals(sessaoData[0])).findFirst().orElse(null);
                        Sala sessaoSala = cinema.getSalas().stream().filter(s -> s.getNumero() == Integer.parseInt(sessaoData[1])).findFirst().orElse(null);
                        Sessao sessao = new Sessao(sessaoFilme, sessaoSala, dateFormat.parse(sessaoData[2])); // Adapte para Date
                        cinema.adicionarSessao(sessao);
                        break;
                    case "Ingresso":
                        String[] ingressoData = parts[1].split(",");
                        Cliente cliente = new Cliente(ingressoData[0], ingressoData[0]);
                        Filme ingressoFilme = cinema.getFilmes().stream().filter(f -> f.getTitulo().equals(ingressoData[1])).findFirst().orElse(null);
                        Sala ingressoSala = cinema.getSalas().stream().filter(s -> s.getNumero() == Integer.parseInt(ingressoData[2])).findFirst().orElse(null);
                        Sessao ingressoSessao = cinema.getSessoes().stream().filter(s -> s.getFilme().equals(ingressoFilme) && s.getSala().equals(ingressoSala)).findFirst().orElse(null);
                        Assento ingressoAssento = ingressoSessao.getSala().getAssentos().stream().filter(a -> a.getNumero() == Integer.parseInt(ingressoData[3])).findFirst().orElse(null);
                        Ingresso ingresso = new Ingresso(cliente, ingressoSessao, ingressoAssento);
                        cinema.adicionarIngresso(ingresso);
                        break;
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}