package br.senac.rj.banco.modelo;

//Alunos: Guilherme Lopes, Breno Costa & Gabriel Da Cunha
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class Fabricante {
	private Integer fabricanteID;
	private String nome;
	private String localizacao;
	private String descricao;

	public Fabricante(Integer fabricanteID, String nome, String localizacao, String descricao) {
		setFabricanteID(fabricanteID);
		setNome(nome);
		setLocalizacao(localizacao);
		setDescricao(descricao);
	}

	public Fabricante() {
	}

	public Integer getFabricanteID() {
		return fabricanteID;
	}

	public void setFabricanteID(Integer fabricanteID) {
		if (fabricanteID != null && fabricanteID > 0) {
			this.fabricanteID = fabricanteID;
		} else {
			System.out.println("Erro: FabricanteID deve ser um número inteiro positivo.");
		}
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		if (nome != null && nome.matches("^[A-Za-z ]{1,50}$")) {
			this.nome = nome.substring(0, 1).toUpperCase() + nome.substring(1).toLowerCase();
		} else {
			System.out.println("Erro: Nome deve ter no máximo 50 caracteres e conter apenas letras.");
		}
	}

	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(String localizacao) {
		if (localizacao != null && localizacao.matches("^[A-Za-z0-9,.-]+$")) {
			this.localizacao = localizacao.substring(0, Math.min(localizacao.length(), 255));
		} else {
			System.out.println("Erro: Localizacao deve conter apenas letras, números, ',', '.' e '-'.");
		}
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao.substring(0, Math.min(descricao.length(), 255));
	}

	@Override
	public String toString() {
		return "Fabricante{" + "fabricanteID=" + fabricanteID + ", nome='" + nome + '\'' + ", localizacao='"
				+ localizacao + '\'' + ", descricao='" + descricao + '\'' + '}';
	}

	public boolean adicionarFabricante(int id, String nome, String localizacao, String descricao) {
		Connection conexao = null;
		PreparedStatement ps = null;

		try {
			conexao = Conexao.conectaBanco();
			String sql = "INSERT INTO Fabricante (FabricanteID, Nome, Localizacao, Descricao) VALUES (?, ?, ?, ?)";
			ps = conexao.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setString(2, nome);
			ps.setString(3, localizacao);
			ps.setString(4, descricao);

			int linhasAfetadas = ps.executeUpdate();
			return linhasAfetadas > 0;
		} catch (SQLException erro) {
			System.out.println("Erro ao adicionar o Fabricante: " + erro.toString());
			return false;
		} finally {
			Conexao.fechaConexao(conexao);
		}
	}

	public boolean removerFabricante(int fabricanteID) {
		Connection conexao = null;
		PreparedStatement ps = null;

		try {
			conexao = Conexao.conectaBanco();
			String sql = "DELETE FROM Fabricante WHERE FabricanteID = ?";
			ps = conexao.prepareStatement(sql);
			ps.setInt(1, fabricanteID);

			int linhasAfetadas = ps.executeUpdate();
			return linhasAfetadas > 0;
		} catch (SQLException erro) {
			System.out.println("Erro ao remover o Fabricante: " + erro.toString());
			return false;
		} finally {
			Conexao.fechaConexao(conexao);
		}
	}

	public Fabricante consultarFabricantePorNome(String nome) {
		Connection conexao = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conexao = Conexao.conectaBanco();
			String sql = "SELECT * FROM Fabricante WHERE Nome = ?";
			ps = conexao.prepareStatement(sql);
			ps.setString(1, nome);

			rs = ps.executeQuery();

			if (rs.next()) {
				Fabricante fabricante = new Fabricante();
				fabricante.fabricanteID = rs.getInt("FabricanteID");
				fabricante.nome = rs.getString("Nome");
				fabricante.localizacao = rs.getString("Localizacao");
				fabricante.descricao = rs.getString("Descricao");
				return fabricante;
			} else {
				return null;
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao consultar o Fabricante: " + erro.toString());
			return null;
		} finally {
			Conexao.fechaConexao(conexao);
		}
	}

	public boolean consultarFabricante(int fabricanteID) {
		Connection conexao = null;

		try {
			conexao = Conexao.conectaBanco();
			String sql = "SELECT * FROM Fabricante WHERE FabricanteID=?";
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setInt(1, fabricanteID);
			ResultSet rs = ps.executeQuery();

			if (!rs.isBeforeFirst()) {
				System.out.println("Fabricante não encontrado!");
				return false;
			} else {
				while (rs.next()) {
					this.fabricanteID = rs.getInt("FabricanteID");
					this.nome = rs.getString("Nome");
					this.localizacao = rs.getString("Localizacao");
					this.descricao = rs.getString("Descricao");
				}
				return true;
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao consultar o fabricante: " + erro.toString());
			return false;
		} finally {
			Conexao.fechaConexao(conexao);
		}
	}

	public boolean atualizarFabricante(String nome) {
		if (this.fabricanteID == null || this.fabricanteID <= 0) {
			System.out.println("Erro: FabricanteID inválido para atualização.");
			return false;
		}

		Connection conexao = null;
		try {
			conexao = Conexao.conectaBanco();
			String sql = "UPDATE Fabricante SET Nome=? WHERE FabricanteID=?";
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setString(1, nome);
			ps.setInt(2, this.fabricanteID);
			int totalRegistrosAfetados = ps.executeUpdate();
			if (totalRegistrosAfetados == 0) {
				System.out.println("Não foi feita a atualização!");
			} else {
				System.out.println("Atualização realizada!");
			}
			return true;
		} catch (SQLException erro) {
			System.out.println("Erro ao atualizar o fabricante: " + erro.toString());
			return false;
		} finally {
			Conexao.fechaConexao(conexao);
		}
	}
}
