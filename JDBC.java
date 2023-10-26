import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ConsultaLivrosEmprestados {

    public static void main(String[] args) {
        // Configuração de conexão com o banco de dados (substitua com suas informações)
        String url = "jdbc:mysql://localhost:3306/seu_banco_de_dados";
        String usuario = "seu_usuario";
        String senha = "sua_senha";

        // Solicitar o nome do usuário
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o nome do usuário:");
        String nomeUsuario = scanner.nextLine();

        // Consulta SQL para obter os livros emprestados
        String consultaSQL = "SELECT livro FROM emprestimos WHERE usuario = ?";

        try {
            // Estabelecer a conexão com o banco de dados
            Connection conexao = DriverManager.getConnection(url, usuario, senha);

            // Preparar a consulta SQL
            PreparedStatement preparedStatement = conexao.prepareStatement(consultaSQL);
            preparedStatement.setString(1, nomeUsuario);

            // Executar a consulta
            ResultSet resultSet = preparedStatement.executeQuery();

            // Exibir os livros emprestados
            while (resultSet.next()) {
                String livro = resultSet.getString("livro");
                System.out.println("Livro emprestado: " + livro);
            }

            // Fechar recursos
            resultSet.close();
            preparedStatement.close();
            conexao.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}
