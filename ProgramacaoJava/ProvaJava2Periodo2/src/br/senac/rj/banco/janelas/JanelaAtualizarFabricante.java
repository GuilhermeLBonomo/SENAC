package br.senac.rj.banco.janelas;

//Alunos: Guilherme Lopes, Breno Costa & Gabriel Da Cunha
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import br.senac.rj.banco.modelo.Conexao;

public final class JanelaAtualizarFabricante {

	public static JFrame criarJanelaAtualizarFabricante() {
		JFrame janelaAtualizarFabricante = new JFrame("Atualizar Fabricante");
		janelaAtualizarFabricante.setResizable(false);
		janelaAtualizarFabricante.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		janelaAtualizarFabricante.setSize(400, 300);

		Container caixa = janelaAtualizarFabricante.getContentPane();
		caixa.setLayout(null);

		JLabel labelSelecionar = new JLabel("Selecione o Fabricante:");
		labelSelecionar.setBounds(50, 40, 150, 20);

		JComboBox<String> comboBoxFabricantes = new JComboBox<>();
		comboBoxFabricantes.setBounds(200, 40, 150, 20);

		// Carrega os fabricantes no JComboBox
		carregarFabricantesComboBox(comboBoxFabricantes);

		JLabel labelNovoNome = new JLabel("Novo Nome:");
		JLabel labelNovaDescricao = new JLabel("Nova Descrição:");
		JLabel labelNovaLocalizacao = new JLabel("Nova Localização:");

		labelNovoNome.setBounds(50, 80, 100, 20);
		labelNovaDescricao.setBounds(50, 120, 120, 20);
		labelNovaLocalizacao.setBounds(50, 160, 120, 20);

		JTextField jTextNovoNome = new JTextField();
		JTextField jTextNovaDescricao = new JTextField();
		JTextField jTextNovaLocalizacao = new JTextField();

		jTextNovoNome.setBounds(200, 80, 150, 20);
		jTextNovaDescricao.setBounds(200, 120, 150, 20);
		jTextNovaLocalizacao.setBounds(200, 160, 150, 20);

		JButton botaoAtualizar = new JButton("Atualizar");
		botaoAtualizar.setBounds(50, 200, 150, 20);

		botaoAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String novoNome = jTextNovoNome.getText().trim();
					String novaDescricao = jTextNovaDescricao.getText().trim();
					String novaLocalizacao = jTextNovaLocalizacao.getText().trim();
					String fabricanteSelecionado = (String) comboBoxFabricantes.getSelectedItem();

					if (fabricanteSelecionado == null || fabricanteSelecionado.isEmpty()) {
						JOptionPane.showMessageDialog(janelaAtualizarFabricante, "Selecione um fabricante válido");
						return;
					}

					int resposta = JOptionPane.showConfirmDialog(janelaAtualizarFabricante,
							"Deseja atualizar o fabricante?", "Confirmação", JOptionPane.YES_NO_OPTION);

					if (resposta == JOptionPane.YES_OPTION) {
						Connection conexao = null;
						PreparedStatement ps = null;

						try {
							conexao = Conexao.conectaBanco();
							String consultaIdSql = "SELECT FabricanteID FROM Fabricante WHERE Nome = ?";
							ps = conexao.prepareStatement(consultaIdSql);
							ps.setString(1, fabricanteSelecionado);
							ResultSet rs = ps.executeQuery();

							int fabricanteId = 0;
							if (rs.next()) {
								fabricanteId = rs.getInt("FabricanteID");
							}

							String sql = "UPDATE Fabricante SET Nome = ?, Descricao = ?, Localizacao = ? WHERE FabricanteID = ?";
							ps = conexao.prepareStatement(sql);
							ps.setString(1, novoNome);
							ps.setString(2, novaDescricao);
							ps.setString(3, novaLocalizacao);
							ps.setInt(4, fabricanteId);

							int linhasAfetadas = ps.executeUpdate();

							if (linhasAfetadas > 0) {
								JOptionPane.showMessageDialog(janelaAtualizarFabricante, "Fabricante atualizado!");
								janelaAtualizarFabricante.dispose();
							} else {
								JOptionPane.showMessageDialog(janelaAtualizarFabricante,
										"Erro ao atualizar o Fabricante!");
							}
						} finally {
							Conexao.fechaConexao(conexao);
						}
					}
				} catch (Exception erro) {
					JOptionPane.showMessageDialog(janelaAtualizarFabricante, "Erro ao atualizar o Fabricante!");
				}
			}
		});

		janelaAtualizarFabricante.add(labelSelecionar);
		janelaAtualizarFabricante.add(comboBoxFabricantes);
		janelaAtualizarFabricante.add(labelNovoNome);
		janelaAtualizarFabricante.add(jTextNovoNome);
		janelaAtualizarFabricante.add(labelNovaDescricao);
		janelaAtualizarFabricante.add(jTextNovaDescricao);
		janelaAtualizarFabricante.add(labelNovaLocalizacao);
		janelaAtualizarFabricante.add(jTextNovaLocalizacao);
		janelaAtualizarFabricante.add(botaoAtualizar);

		return janelaAtualizarFabricante;
	}

	private static void carregarFabricantesComboBox(JComboBox<String> comboBox) {
		Connection conexao = null;
		PreparedStatement ps = null;

		try {
			conexao = Conexao.conectaBanco();
			String consultaFabricantesSql = "SELECT Nome FROM Fabricante";
			ps = conexao.prepareStatement(consultaFabricantesSql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				String nomeFabricante = rs.getString("Nome");
				comboBox.addItem(nomeFabricante);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Conexao.fechaConexao(conexao);
		}
	}
}
