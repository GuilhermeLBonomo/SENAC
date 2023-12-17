package br.senac.rj.banco.janelas;

//Alunos: Guilherme Lopes, Breno Costa & Gabriel Da Cunha
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import br.senac.rj.banco.modelo.Conexao;

public final class JanelaRemoverFabricante {
	public static JFrame criarJanelaRemoverFabricante() {
		JFrame janelaRemoverFabricante = new JFrame("Remover Fabricante");
		janelaRemoverFabricante.setResizable(false);
		janelaRemoverFabricante.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		janelaRemoverFabricante.setSize(400, 200);

		Container caixa = janelaRemoverFabricante.getContentPane();
		caixa.setLayout(null);

		JLabel labelFabricanteID = new JLabel("ID do Fabricante: ");
		labelFabricanteID.setBounds(50, 40, 100, 20);

		JTextField jTextFabricanteID = new JTextField();
		jTextFabricanteID.setBounds(180, 40, 50, 20);

		JButton botaoRemover = new JButton("Remover");
		botaoRemover.setBounds(50, 80, 150, 20);

		botaoRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int fabricanteID = Integer.parseInt(jTextFabricanteID.getText());
					int resposta = JOptionPane.showConfirmDialog(janelaRemoverFabricante,
							"Deseja realmente remover o Fabricante?", "Confirmação", JOptionPane.YES_NO_OPTION);

					if (resposta == JOptionPane.YES_OPTION) {
						Connection conexao = null;
						PreparedStatement ps = null;

						try {
							conexao = Conexao.conectaBanco();
							String sql = "DELETE FROM Fabricante WHERE FabricanteID = ?";
							ps = conexao.prepareStatement(sql);
							ps.setInt(1, fabricanteID);

							int linhasAfetadas = ps.executeUpdate();

							if (linhasAfetadas > 0) {
								JOptionPane.showMessageDialog(janelaRemoverFabricante, "Fabricante removido!");
								janelaRemoverFabricante.dispose();
							} else {
								JOptionPane.showMessageDialog(janelaRemoverFabricante, "Erro ao remover o Fabricante!");
							}
						} catch (SQLException e1) {
							e1.printStackTrace();
						} finally {
							Conexao.fechaConexao(conexao);
						}
					}
				} catch (NumberFormatException erro) {
					JOptionPane.showMessageDialog(janelaRemoverFabricante, "Erro ao remover o Fabricante!");
				}
			}
		});

		janelaRemoverFabricante.add(labelFabricanteID);
		janelaRemoverFabricante.add(jTextFabricanteID);
		janelaRemoverFabricante.add(botaoRemover);

		return janelaRemoverFabricante;
	}
}
