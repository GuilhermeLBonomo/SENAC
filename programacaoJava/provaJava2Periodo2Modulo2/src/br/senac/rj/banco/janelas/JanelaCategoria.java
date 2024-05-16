package br.senac.rj.banco.janelas;

//Alunos: Guilherme Lopes, Breno Costa & Gabriel Da Cunha
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import br.senac.rj.banco.modelo.Categoria;

public final class JanelaCategoria {

	private static JTextField jTextId;
	private static JTextField jTextNome;
	private static JTextField jTextStatus;
	private static JTextField jTextDescricao;
	private static JButton botaoConsultar;
	private static JButton botaoGravar;
	private static JButton botaoExcluir;

	public static JFrame criarJanelaCategoria() {
		JFrame janelaCategoria = new JFrame("Atualização de categoria");
		janelaCategoria.setResizable(false);
		janelaCategoria.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		janelaCategoria.setSize(400, 300);
		Container caixa = janelaCategoria.getContentPane();
		caixa.setLayout(null);

		JLabel labelId = new JLabel("ID: ");
		JLabel labelNome = new JLabel("Nome: ");
		JLabel labelStatus = new JLabel("Status: ");
		JLabel labelDescricao = new JLabel("Descricao: ");
		labelId.setBounds(50, 40, 100, 20);
		labelNome.setBounds(50, 80, 150, 20);
		labelStatus.setBounds(50, 120, 100, 20);
		labelDescricao.setBounds(50, 160, 100, 20);

		jTextId = new JTextField();
		jTextNome = new JTextField();
		jTextStatus = new JTextField();
		jTextDescricao = new JTextField();
		jTextId.setEnabled(true);
		jTextNome.setEnabled(true);
		jTextStatus.setEnabled(false);
		jTextDescricao.setEnabled(false);
		jTextId.setBounds(180, 40, 50, 20);
		jTextNome.setBounds(180, 80, 150, 20);
		jTextStatus.setBounds(180, 120, 150, 20);
		jTextDescricao.setBounds(180, 160, 150, 20);

		janelaCategoria.add(labelId);
		janelaCategoria.add(labelNome);
		janelaCategoria.add(labelStatus);
		janelaCategoria.add(labelDescricao);
		janelaCategoria.add(jTextId);
		janelaCategoria.add(jTextNome);
		janelaCategoria.add(jTextStatus);
		janelaCategoria.add(jTextDescricao);

		botaoConsultar = new JButton("Consultar");
		botaoConsultar.setBounds(230, 40, 100, 20);
		janelaCategoria.add(botaoConsultar);

		botaoGravar = new JButton("Gravar");
		botaoGravar.setBounds(50, 200, 100, 20);
		botaoGravar.setEnabled(false);
		janelaCategoria.add(botaoGravar);

		botaoExcluir = new JButton("Excluir");
		botaoExcluir.setBounds(150, 200, 100, 20);
		janelaCategoria.add(botaoExcluir);

		JButton botaoLimpar = new JButton("Limpar");
		botaoLimpar.setBounds(250, 200, 100, 20);
		janelaCategoria.add(botaoLimpar);

		botaoConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int CategoriaID = Integer.parseInt(jTextId.getText());
					botaoGravar.setEnabled(true);
					botaoExcluir.setEnabled(true);
					String Nome;
					String Status;
					String Descricao;
					Categoria categoria = new Categoria();
					if (!categoria.consultarCategoria(CategoriaID)) {
						Nome = "";
						Status = "";
						Descricao = "";
					} else {
						Nome = categoria.getNome();
						Status = categoria.getStatus();
						Descricao = categoria.getDescricao();
					}

					jTextNome.setText(Nome);
					jTextStatus.setText(Status);
					jTextDescricao.setText(Descricao);
					jTextId.setEnabled(false);
					botaoConsultar.setEnabled(false);
					jTextNome.setEnabled(true);
					jTextStatus.setEnabled(true);
					jTextDescricao.setEnabled(true);
					jTextNome.requestFocus();
				} catch (Exception erro) {
					JOptionPane.showMessageDialog(janelaCategoria, "Preencha o campo ID corretamente!!");
				}
			}
		});

		botaoGravar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int resposta = JOptionPane.showConfirmDialog(janelaCategoria, "Deseja atualizar?", "Confirmação",
						JOptionPane.YES_NO_OPTION);

				if (resposta == JOptionPane.YES_OPTION) {
					int CategoriaID = Integer.parseInt(jTextId.getText());
					String Nome = jTextNome.getText().trim();
					String Status = jTextStatus.getText().trim();
					String Descricao = jTextDescricao.getText().trim();

					if (Nome.length() == 0) {
						JOptionPane.showMessageDialog(janelaCategoria, "Preencha o campo nome");
						jTextNome.requestFocus();
					} else {
						Categoria categoria = new Categoria();

						if (!categoria.consultarCategoria(CategoriaID)) {
							if (!categoria.cadastrarCategoria(CategoriaID, Nome, Status, Descricao)) {
								JOptionPane.showMessageDialog(janelaCategoria, "Erro na inclusão da categoria!");
							} else {
								JOptionPane.showMessageDialog(janelaCategoria, "Inclusão realizada!");
								limparCampos();
							}
						} else {
							if (!categoria.atualizarCategoria(CategoriaID, Nome, Status, Descricao)) {
								JOptionPane.showMessageDialog(janelaCategoria, "Erro na atualização!");
							} else {
								JOptionPane.showMessageDialog(janelaCategoria, "Alteração realizada!");
								limparCampos();
							}
						}
					}
				}
			}
		});

		botaoExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int resposta = JOptionPane.showConfirmDialog(janelaCategoria, "Deseja excluir?", "Confirmação",
						JOptionPane.YES_NO_OPTION);
				if (resposta == JOptionPane.YES_OPTION) {
					int CategoriaID = Integer.parseInt(jTextId.getText());
					Categoria categoria = new Categoria();
					if (categoria.excluirCategoria(CategoriaID)) {
						JOptionPane.showMessageDialog(janelaCategoria, "Exclusão realizada!");
						limparCampos();
					} else {
						JOptionPane.showMessageDialog(janelaCategoria, "Erro ao excluir a categoria!");
					}
				}
			}
		});

		botaoLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparCampos();
			}
		});

		return janelaCategoria;
	}

	private static void limparCampos() {
		jTextId.setText("");
		jTextNome.setText("");
		jTextStatus.setText("");
		jTextDescricao.setText("");
		jTextId.setEnabled(true);
		jTextNome.setEnabled(true);
		jTextStatus.setEnabled(false);
		jTextDescricao.setEnabled(false);
		botaoConsultar.setEnabled(true);
		botaoGravar.setEnabled(false);
		botaoExcluir.setEnabled(false);
		jTextId.requestFocus();
	}
}
