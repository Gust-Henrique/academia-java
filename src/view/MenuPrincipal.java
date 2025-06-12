package view;

import controller.AlunoController;
import controller.PlanoController;
import controller.FuncionarioController;
import model.Plano;

import java.util.Scanner;

public class MenuPrincipal {
    private final AlunoController alunoController = new AlunoController();
    private final PlanoController planoController = new PlanoController();
    private final FuncionarioController funcionarioController = new FuncionarioController();
    private final Scanner scanner = new Scanner(System.in);

    public void exibirMenu() {
        int opcao;

        do {
            System.out.println("\n=== MENU PRINCIPAL - SISTEMA DE ACADEMIA ===");
            System.out.println("1. Cadastrar plano");
            System.out.println("2. Listar planos");
            System.out.println("3. Remover plano");
            System.out.println("4. Cadastrar aluno");
            System.out.println("5. Listar alunos");
            System.out.println("6. Remover aluno");
            System.out.println("7. Cadastrar funcionário");
            System.out.println("8. Listar funcionários");
            System.out.println("9. Remover funcionário");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> cadastrarPlano();
                case 2 -> planoController.listarPlanos();
                case 3 -> removerPlano();
                case 4 -> cadastrarAluno();
                case 5 -> alunoController.listarAlunos();
                case 6 -> removerAluno();
                case 7 -> cadastrarFuncionario();
                case 8 -> funcionarioController.listarFuncionarios();
                case 9 -> removerFuncionario();
                case 0 -> System.out.println("Encerrando...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    /*private void cadastrarPlano() {
        System.out.print("Nome do plano: ");
        String nome = scanner.nextLine();
        System.out.print("Preço do plano: R$ ");
        double preco = scanner.nextDouble();
        scanner.nextLine();

        planoController.cadastrarPlano(nome, preco);
    }*/

    private void cadastrarPlano() {
    System.out.print("Nome do plano: ");
    String nome = scanner.nextLine();
    System.out.print("Preço do plano: R$ ");
    double preco = scanner.nextDouble();
    scanner.nextLine();

    funcionarioController.listarFuncionarios();
    System.out.print("Escolha o número do responsável pelo plano: ");
    int indiceResponsavel = scanner.nextInt() - 1;
    scanner.nextLine();

    Funcionario responsavel = funcionarioController.buscarFuncionarioPorIndice(indiceResponsavel);
    if (responsavel == null) {
        System.out.println("Funcionário inválido. Cadastro cancelado.");
        return;
    }

    planoController.cadastrarPlano(nome, preco, responsavel);
    }

    private void removerPlano() {
        planoController.listarPlanos();
        System.out.print("Informe o número do plano a remover: ");
        int indice = scanner.nextInt() - 1;
        scanner.nextLine();

        planoController.removerPlano(indice);
    }

    private void cadastrarAluno() {
        System.out.print("Nome do aluno: ");
        String nome = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Matrícula: ");
        String matricula = scanner.nextLine();

        planoController.listarPlanos();
        System.out.print("Escolha o número do plano: ");
        int indicePlano = scanner.nextInt() - 1;
        scanner.nextLine();

        Plano planoEscolhido = planoController.buscarPlanoPorIndice(indicePlano);
        if (planoEscolhido == null) {
            System.out.println("Plano inválido. Cadastro cancelado.");
            return;
        }

        alunoController.cadastrarAluno(nome, cpf, email, telefone, matricula, planoEscolhido);
    }

    private void removerAluno() {
        alunoController.listarAlunos();
        System.out.print("Informe o número do aluno a remover: ");
        int indice = scanner.nextInt() - 1;
        scanner.nextLine();

        alunoController.removerAluno(indice);

    }


    private void cadastrarFuncionario() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Cargo: ");
        String cargo = scanner.nextLine();

        funcionarioController.cadastrarFuncionario(nome, cpf, email, telefone, cargo);
    }

    private void removerFuncionario() {
        funcionarioController.listarFuncionarios();
        System.out.print("Número do funcionário a remover: ");
        int indice = scanner.nextInt() - 1;
        scanner.nextLine();

        funcionarioController.removerFuncionario(indice);
    }

}
