package br.senac.rj.banco.modelo;

//Alunos: Guilherme Lopes, Breno Costa & Gabriel Da Cunha
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class Produto {
	private int produtoID;
	private String nome;
	private double preco;
	private int fabricanteID;
	private int categoriaID;

	public Produto() {
	}

	public Produto(int produtoID, String nome, double preco, int fabricanteID, int categoriaID) {
		this.produtoID = produtoID;
		this.nome = nome;
		this.preco = preco;
		this.fabricanteID = fabricanteID;
		this.categoriaID = categoriaID;
	}

	public int getProdutoID() {
		return produtoID;
	}

	public void setProdutoID(int produtoID) {
		this.produtoID = produtoID;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public int getFabricanteID() {
		return fabricanteID;
	}

	public void setFabricanteID(int fabricanteID) {
		this.fabricanteID = fabricanteID;
	}

	public int getCategoriaID() {
		return categoriaID;
	}

	public void setCategoriaID(int categoriaID) {
		this.categoriaID = categoriaID;
	}

	public String obterNomeFabricante() {
		Connection conexao = null;
		try {
			conexao = Conexao.conectaBanco();
			String sql = "SELECT Nome FROM Fabricante WHERE FabricanteID = ?;";
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setInt(1, this.fabricanteID);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getString("Nome");
			}
		} catch (SQLException e) {
			System.out.println("Erro ao obter nome do fabricante: " + e.toString());
		} finally {
			Conexao.fechaConexao(conexao);
		}
		return null;
	}

	public String obterNomeCategoria() {
		Connection conexao = null;
		try {
			conexao = Conexao.conectaBanco();
			String sql = "SELECT Nome FROM Categoria WHERE CategoriaID = ?;";
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setInt(1, this.categoriaID);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getString("Nome");
			}
		} catch (SQLException e) {
			System.out.println("Erro ao obter nome da categoria: " + e.toString());
		} finally {
			Conexao.fechaConexao(conexao);
		}
		return null;
	}

	@Override
	public String toString() {
		return "Id: " + produtoID + '\n' + "Nome: " + nome + '\n' + "Preço: " + preco + '\n' + "FabricanteID: "
				+ fabricanteID + '\n' + "CategoriaID: " + categoriaID;
	}

	public static boolean adicionarProduto(String nome, double preco, int fabricanteID, int categoriaID,
			int produtoId) {
		Connection conexao = null;
		try {
			conexao = Conexao.conectaBanco();
			String sql = "INSERT INTO Produto (Nome, Preco, FabricanteID, CategoriaID, ProdutoID) VALUES (?, ?, ?, ?, ?);";
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setString(1, nome);
			ps.setDouble(2, preco);
			ps.setInt(3, fabricanteID);
			ps.setInt(4, categoriaID);
			ps.setInt(5, produtoId);
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

	public boolean atualizarProduto(String nome, double preco, int fabricanteID, int categoriaID, int produtoId) {
		if (!consultarProdutoPorID(produtoID))
			return false;

		else {
			Connection conexao = null;
			try {
				conexao = Conexao.conectaBanco();
				String sql = "UPDATE Produto SET Nome=?, Preco=?, FabricanteID=?, CategoriaID=? WHERE ProdutoID=?;";
				PreparedStatement ps = conexao.prepareStatement(sql);
				ps.setString(1, nome);
				ps.setDouble(2, preco);
				ps.setInt(3, fabricanteID);
				ps.setInt(4, categoriaID);
				ps.setInt(5, produtoId);
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

	public boolean consultarProdutoPorID(int produtoID) {
		Connection conexao = null;
		try {
			conexao = Conexao.conectaBanco();
			String sql = "SELECT * FROM Produto WHERE ProdutoID=?";
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setInt(1, produtoID);
			ResultSet rs = ps.executeQuery();
			if (!rs.isBeforeFirst()) {
				System.out.println("Produto não cadastrado!");
				return false;
			} else {
				while (rs.next()) {
					this.produtoID = rs.getInt("ProdutoID");
					this.nome = rs.getString("Nome");
					this.categoriaID = rs.getInt("CategoriaID");
					this.fabricanteID = rs.getInt("FabricanteID");
					this.preco = rs.getDouble("Preco");
				}
				return true;
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao consultar o produto: " + erro.toString());
			return false;
		} finally {
			Conexao.fechaConexao(conexao);
		}

	}

	public boolean deletarProduto(int ProdutoID) {
		Connection conexao = null;
		try {
			conexao = Conexao.conectaBanco();
			String sql = "DELETE FROM Produto WHERE ProdutoID = ?;";
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setInt(1, ProdutoID);
			int totalRegistrosModificados = ps.executeUpdate();
			return totalRegistrosModificados >= 1;
		} catch (SQLException e) {
			System.out.println("Erro ao deletar produto: " + e.toString());
			return false;
		} finally {
			Conexao.fechaConexao(conexao);
		}
	}

	public static Produto obterProdutoPorID(int produtoID) {
		Connection conexao = null;
		try {
			conexao = Conexao.conectaBanco();
			String sql = "SELECT * FROM Produto WHERE ProdutoID = ?;";
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setInt(1, produtoID);
			ResultSet rs = ps.executeQuery();
			if (!rs.isBeforeFirst()) {
				System.out.println("Produto não encontrado!");
				return null;
			} else {
				Produto produto = new Produto();
				while (rs.next()) {
					produto.setProdutoID(rs.getInt("ProdutoID"));
					produto.setNome(rs.getString("Nome"));
					produto.setCategoriaID(rs.getInt("CategoriaID"));
					produto.setFabricanteID(rs.getInt("FabricanteID"));
					produto.setPreco(rs.getDouble("Preco"));
				}
				return produto;
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao obter o produto por ID: " + erro.toString());
			return null;
		} finally {
			Conexao.fechaConexao(conexao);
		}
	}

	public static List<Produto> obterProdutosOrdenados() {
		List<Produto> produtos = new ArrayList<>();
		Connection conexao = null;
		try {
			conexao = Conexao.conectaBanco();
			String sql = "SELECT * FROM Produto ORDER BY FabricanteID, CategoriaID;";
			PreparedStatement ps = conexao.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int produtoID = rs.getInt("ProdutoID");
				String nome = rs.getString("Nome");
				double preco = rs.getDouble("Preco");
				int fabricanteID = rs.getInt("FabricanteID");
				int categoriaID = rs.getInt("CategoriaID");
				produtos.add(new Produto(produtoID, nome, preco, fabricanteID, categoriaID));
			}
		} catch (SQLException e) {
			System.out.println("Erro ao obter produtos: " + e.toString());
		} finally {
			Conexao.fechaConexao(conexao);
		}
		return produtos;
	}
}
