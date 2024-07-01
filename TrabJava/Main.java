import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Alunos alunos = new Alunos();
        alunos.connect(); // Conecta ao banco de dados dos alunos
        alunos.createTable(); // Cria a tabela de alunos, se não existir

        Professores professores = new Professores();
        professores.connect(); // Conecta ao banco de dados dos professores
        professores.createTable(); // Cria a tabela de professores, se não existir

        Scanner scanner = new Scanner(System.in);
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\nEscolha uma opção:");
            System.out.println("1 - Adicionar aluno");
            System.out.println("2 - Consultar alunos");
            System.out.println("3 - Deletar aluno por ID");
            System.out.println("4 - Adicionar professor");
            System.out.println("5 - Consultar professores");
            System.out.println("6 - Deletar professor por ID");
            System.out.println("0 - Sair");

            System.out.print("Opção escolhida: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do scanner

            switch (opcao) {
                case 1:
                    System.out.print("Nome do aluno: ");
                    String nomeAluno = scanner.nextLine();
                    System.out.print("Idade do aluno: ");
                    int idadeAluno = scanner.nextInt();
                    scanner.nextLine(); // Limpar o buffer do scanner
                    System.out.print("Aulas do aluno: ");
                    String aulasAluno = scanner.nextLine();
                    alunos.inserirAluno(nomeAluno, idadeAluno, aulasAluno);
                    break;
                case 2:
                    alunos.consultarAluno();
                    break;
                case 3:
                    System.out.print("Digite o ID do aluno a ser deletado: ");
                    int idAluno = scanner.nextInt();
                    scanner.nextLine(); // Limpar o buffer do scanner
                    alunos.deleteAluno(idAluno);
                    break;
                case 4:
                    System.out.print("Nome do professor: ");
                    String nomeProf = scanner.nextLine();
                    System.out.print("Idade do professor: ");
                    int idadeProf = scanner.nextInt();
                    scanner.nextLine(); // Limpar o buffer do scanner
                    System.out.print("Profissão do professor: ");
                    String profissaoProf = scanner.nextLine();
                    professores.inserirProfessor(nomeProf, idadeProf, profissaoProf);
                    break;
                case 5:
                    professores.consultarProfessores();
                    break;
                case 6:
                    System.out.print("Digite o ID do professor a ser deletado: ");
                    int idProf = scanner.nextInt();
                    scanner.nextLine(); // Limpar o buffer do scanner
                    professores.deleteProfessor(idProf);
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
                    break;
            }
        }

        alunos.close(); // Fecha a conexão com o banco de dados dos alunos
        professores.close(); // Fecha a conexão com o banco de dados dos professores
        scanner.close();
    }
}