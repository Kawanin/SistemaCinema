import model.Atendente;
import model.Funcionario;
import model.Gerente;
import view.FuncionarioView;

public class FuncionarioController {
    private FuncionarioView view;

    public FuncionarioController(FuncionarioView view) {
        this.view = view;
    }

    public Funcionario criarFuncionario(String tipo) {
        String nome = view.lerNome();
        String cpf = view.lerCpf();

        if (tipo.equals("Atendente")) {
            return new Atendente(nome, cpf);
        } else if (tipo.equals("Gerente")) {
            return new Gerente(nome, cpf);
        } else {
            return null;
        }
    }
}