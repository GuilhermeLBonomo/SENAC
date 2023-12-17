package br.senac.rj.banco.modelo;

// Alunos: Guilherme Lopes, Breno Costa & Gabriel Da Cunha
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class Conexao {

	public static Connection conectaBanco() {
		Connection conexao = null;
		try {
			final String DRIVER = "org.mariadb.jdbc.Driver";
			final String URL = "jdbc:mariadb://localhost:3306/Producao";
			final String USER = "ProvaReinaldo";
			final String PASSWORD = "Reinaldo123";
			/*final String DRIVER = "com.mysql.cj.jdbc.Driver";
			final String URL = "jdbc:mysql://localhost/teste1";
			final String USER = "root";
			final String PASSWORD = "";*/

			Class.forName(DRIVER);
			conexao = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (ClassNotFoundException erro) {
			System.err.println("Driver não encontrado: " + erro);
			System.exit(1);
		} catch (SQLException erro) {
			System.err.println("Erro de conexão ao banco de dados: " + erro.toString());
			System.exit(1);
		} catch (Exception erro) {
			System.err.println("Erro não identificado: " + erro.toString());
			System.exit(1);
		}
		return conexao;
	}

	public static void fechaConexao(Connection conexao) {
		try {
			if (conexao != null && !conexao.isClosed()) {
				conexao.close();
			}
		} catch (Exception erro) {
			System.err.println("Erro ao fechar a conexão: " + erro.toString());
		}
	}
}
