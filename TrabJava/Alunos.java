import java.sql.*;
import java.util.List;

public class Alunos {
    private static final String URL = "jdbc:sqlite:C:\\Users\\LabInfo\\Desktop\\K.A\\TrabJava\\banco.db";
    private Connection connection;

    public void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(URL);
            connection.setAutoCommit(false);
            System.out.println("Conexão realizada! Banco de dados: " + URL);
        } catch (ClassNotFoundException e) {
            System.out.println("Driver JDBC do SQLite não encontrado: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Erro ao conectar: " + e.getMessage());
        }
    }

    public void close() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Conexão fechada.");
            } catch (SQLException e) {
                System.out.println("Erro ao fechar a conexão: " + e.getMessage());
            }
        }
    }

    public void createTable() {
        try (Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS Alunos(ID INTEGER PRIMARY KEY, Nome VARCHAR, Idade INTEGER, Aulas VARCHAR)");
            connection.commit();
            System.out.println("Tabela criada ou já existe.");
        } catch (SQLException e) {
            System.out.println("Erro ao criar tabela: " + e.getMessage());
        }
    }

    public void inserirAluno(String nome, int idade, String aulas) {
        try (PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO alunos(Nome, Idade, Aulas) VALUES(?, ?, ?)")) {
            insertStatement.setString(1, nome);
            insertStatement.setInt(2, idade);
            insertStatement.setString(3, aulas);
            insertStatement.executeUpdate();
            connection.commit();

            System.out.println("Aluno inserido: " + nome);
        } catch (SQLException e) {
            System.out.println("Erro ao inserir aluno: " + e.getMessage());
        }
    }

    public void consultarAluno() {
        try (Statement selectStatement = connection.createStatement()) {
            ResultSet resultSet = selectStatement.executeQuery("SELECT * FROM alunos");
            System.out.println("Lista de Alunos:");
            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String nome = resultSet.getString("Nome");
                int idade = resultSet.getInt("Idade");
                String aulas = resultSet.getString("Aulas");
                System.out.println("ID: " + id + ", Nome: " + nome + ", Idade: " + idade + ", Aulas: " + aulas);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao consultar alunos: " + e.getMessage());
        }
    }

    public void deleteAluno(int id) {
        try (PreparedStatement deleteStatement = connection.prepareStatement("DELETE FROM Alunos WHERE ID = ?")) {
            deleteStatement.setInt(1, id);
            int rowsAffected = deleteStatement.executeUpdate();
            connection.commit();
            System.out.println("Aluno deletado.");
        } catch (SQLException e) {
            System.out.println("Erro ao deletar aluno: " + e.getMessage());
        }
    }
}
