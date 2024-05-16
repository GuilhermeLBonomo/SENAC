package br.senac.rj.banco.modelo;

//Alunos: Guilherme Lopes, Breno Costa & Gabriel Da Cunha
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class Categoria {
	private int CategoriaID;
	private String Nome;
	private String Status;
	private String Descricao;
	public static int totalCategorias;

	public Categoria() {
		this.Nome = "";
		Categoria.totalCategorias++;
	}

	Categoria(int CategoriaID) {
		this.CategoriaID = CategoriaID;
	}

	public int getCategoriaID() {
		return CategoriaID;
	}

	public void setCategoriaID(int categoriaID) {
		CategoriaID = categoriaID;
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public String getDescricao() {
		return Descricao;
	}

	public void setDescricao(String descricao) {
		Descricao = descricao;
	}

	public boolean cadastrarCategoria(int CategoriaID, String Nome, String Status, String Descricao) {
		Connection conexao = null;
		try {
			conexao = Conexao.conectaBanco();
			String sql = "INSERT INTO Categoria (CategoriaID, Nome, Status, Descricao) VALUES (?, ?, ?, ?)";
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setInt(1, CategoriaID);
			ps.setString(2, Nome);
			ps.setString(3, Status);
			ps.setString(4, Descricao);
			int totalRegistrosAfetados = ps.executeUpdate();
			if (totalRegistrosAfetados == 0) {
				System.out.println("Não foi feito o cadastro!!");
				return false;
			}
			System.out.println("Cadastro realizado!");
			return true;
		} catch (SQLException erro) {
			System.out.println("Erro ao cadastrar a categoria: " + erro.toString());
			return false;
		} finally {
			Conexao.fechaConexao(conexao);
		}
	}

	public boolean consultarCategoria(int CategoriaId) {
		Connection conexao = null;
		try {
			conexao = Conexao.conectaBanco();
			String sql = "SELECT * FROM Categoria WHERE CategoriaID=?";
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setInt(1, CategoriaId);
			ResultSet rs = ps.executeQuery();
			if (!rs.isBeforeFirst()) {
				System.out.println("Categoria não cadastrada!");
				return false;
			} else {
				while (rs.next()) {
					this.CategoriaID = rs.getInt("CategoriaId");
					this.Nome = rs.getString("Nome");
					this.Status = rs.getString("Status");
					this.Descricao = rs.getString("Descricao");
				}
				return true;
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao consultar a categoria: " + erro.toString());
			return false;
		} finally {
			Conexao.fechaConexao(conexao);
		}
	}

	public boolean atualizarCategoria(int CategoriaId, String Nome, String Status, String Descricao) {
		if (!consultarCategoria(CategoriaId))
			return false;
		else {
			Connection conexao = null;
			try {
				conexao = Conexao.conectaBanco();
				String sql = "UPDATE Categoria SET Nome=?, Status=?, Descricao=? WHERE CategoriaId=?";
				PreparedStatement ps = conexao.prepareStatement(sql);
				ps.setString(1, Nome);
				ps.setString(2, Status);
				ps.setString(3, Descricao);
				ps.setInt(4, CategoriaId);
				int totalRegistrosAfetados = ps.executeUpdate();
				if (totalRegistrosAfetados == 0)
					System.out.println("Não foi feita a atualização!");
				else
					System.out.println("Atualização realizada!");
				return true;
			} catch (SQLException erro) {
				System.out.println("Erro ao atualizar a categoria: " + erro.toString());
				return false;
			} finally {
				Conexao.fechaConexao(conexao);
			}
		}
	}

	public boolean excluirCategoria(int CategoriaId) {
		Connection conexao = null;
		try {
			conexao = Conexao.conectaBanco();
			String sql = "DELETE FROM Categoria WHERE CategoriaID=?";
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setInt(1, CategoriaId);
			int totalRegistrosAfetados = ps.executeUpdate();
			if (totalRegistrosAfetados == 0) {
				System.out.println("Registro não encontrado para exclusão!");
				return false;
			}
			System.out.println("Exclusão realizada!");
			return true;
		} catch (SQLException erro) {
			System.out.println("Erro ao excluir a categoria: " + erro.toString());
			return false;
		} finally {
			Conexao.fechaConexao(conexao);
		}
	}

}
