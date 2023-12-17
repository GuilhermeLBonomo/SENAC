package br.senac.rj.banco.janelas;

//Alunos: Guilherme Lopes, Breno Costa & Gabriel Da Cunha
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import br.senac.rj.banco.modelo.Conexao;

public final class JanelaAdicionarFabricante {
	public static JFrame criarJanelaAdicionarFabricante() {
		JFrame janelaAdicionarFabricante = new JFrame("Adicionar Fabricante");
		janelaAdicionarFabricante.setResizable(false);
		janelaAdicionarFabricante.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		janelaAdicionarFabricante.setSize(400, 300);

		Container caixa = janelaAdicionarFabricante.getContentPane();
		caixa.setLayout(null);

		JLabel labelId = new JLabel("ID: ");
		JLabel labelNome = new JLabel("Nome: ");
		JLabel labelLocalizacao = new JLabel("Localização: ");
		JLabel labelDescricao = new JLabel("Descrição: ");

		labelId.setBounds(50, 10, 100, 20);
		labelNome.setBounds(50, 40, 100, 20);
		labelLocalizacao.setBounds(50, 80, 100, 20);
		labelDescricao.setBounds(50, 120, 100, 20);

		JTextField jTextId = new JTextField();
		JTextField jTextNome = new JTextField();
		JTextField jTextLocalizacao = new JTextField();
		JTextField jTextDescricao = new JTextField();

		jTextId.setBounds(180, 10, 50, 20);
		jTextNome.setBounds(180, 40, 150, 20);
		jTextLocalizacao.setBounds(180, 80, 150, 20);
		jTextDescricao.setBounds(180, 120, 150, 20);

		JButton botaoAdicionar = new JButton("Adicionar");
		botaoAdicionar.setBounds(50, 160, 150, 20);

		botaoAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String idText = jTextId.getText().trim();
					String nome = jTextNome.getText().trim();
					String localizacao = jTextLocalizacao.getText().trim();
					String descricao = jTextDescricao.getText().trim();

					if (idText.isEmpty() || nome.isEmpty()) {
						JOptionPane.showMessageDialog(janelaAdicionarFabricante, "Preencha os campos ID e Nome");
						jTextId.requestFocus();
					} else {
						int id = Integer.parseInt(idText);

						int resposta = JOptionPane.showConfirmDialog(janelaAdicionarFabricante, "Deseja adicionar?",
								"Confirmação", JOptionPane.YES_NO_OPTION);

						if (resposta == JOptionPane.YES_OPTION) {
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

								if (linhasAfetadas > 0) {
									JOptionPane.showMessageDialog(janelaAdicionarFabricante, "Fabricante adicionado!");
									janelaAdicionarFabricante.dispose();
								} else {
									JOptionPane.showMessageDialog(janelaAdicionarFabricante,
											"Erro ao adicionar o Fabricante!");
								}
							} finally {
								Conexao.fechaConexao(conexao);
							}
						}
					}
				} catch (Exception erro) {
					JOptionPane.showMessageDialog(janelaAdicionarFabricante, "Erro ao adicionar o Fabricante!");
				}
			}
		});

		janelaAdicionarFabricante.add(labelId);
		janelaAdicionarFabricante.add(labelNome);
		janelaAdicionarFabricante.add(labelLocalizacao);
		janelaAdicionarFabricante.add(labelDescricao);
		janelaAdicionarFabricante.add(jTextId);
		janelaAdicionarFabricante.add(jTextNome);
		janelaAdicionarFabricante.add(jTextLocalizacao);
		janelaAdicionarFabricante.add(jTextDescricao);
		janelaAdicionarFabricante.add(botaoAdicionar);

		return janelaAdicionarFabricante;
	}
}
