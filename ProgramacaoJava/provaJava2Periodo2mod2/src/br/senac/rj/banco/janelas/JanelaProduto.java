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

import br.senac.rj.banco.modelo.Produto;

public final class JanelaProduto {

	public static JFrame criarJanelaProduto() {
		JFrame janelaProduto = new JFrame("Gerenciamento de Produtos");
		janelaProduto.setResizable(false);
		janelaProduto.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		janelaProduto.setSize(500, 400);
		Container pane = janelaProduto.getContentPane();
		pane.setLayout(null);
		JLabel labelID = new JLabel("ID do Produto:");
		JLabel labelNome = new JLabel("Nome:");
		JLabel labelPreco = new JLabel("Preço:");
		JLabel labelFabricanteID = new JLabel("ID do Fabricante:");
		JLabel labelCategoriaID = new JLabel("ID da Categoria:");
		labelID.setBounds(30, 30, 120, 20);
		labelNome.setBounds(30, 70, 120, 20);
		labelPreco.setBounds(30, 110, 120, 20);
		labelFabricanteID.setBounds(30, 150, 120, 20);
		labelCategoriaID.setBounds(30, 190, 120, 20);

		JTextField textID = new JTextField();
		JTextField textNome = new JTextField();
		JTextField textPreco = new JTextField();
		JTextField textFabricanteID = new JTextField();
		JTextField textCategoriaID = new JTextField();
		textID.setBounds(160, 30, 150, 20);
		textNome.setBounds(160, 70, 150, 20);
		textPreco.setBounds(160, 110, 150, 20);
		textFabricanteID.setBounds(160, 150, 150, 20);
		textCategoriaID.setBounds(160, 190, 150, 20);
		pane.add(labelID);
		pane.add(textID);
		pane.add(labelNome);
		pane.add(textNome);
		pane.add(labelPreco);
		pane.add(textPreco);
		pane.add(labelFabricanteID);
		pane.add(textFabricanteID);
		pane.add(labelCategoriaID);
		pane.add(textCategoriaID);
		JButton botaoAdicionar = new JButton("Adicionar");
		JButton botaoConsultar = new JButton("Consultar");
		JButton botaoAtualizar = new JButton("Atualizar");
		JButton botaoDeletar = new JButton("Deletar");

		botaoAdicionar.setBounds(30, 250, 100, 20);
		botaoConsultar.setBounds(140, 250, 100, 20);
		botaoAtualizar.setBounds(250, 250, 100, 20);
		botaoDeletar.setBounds(360, 250, 100, 20);

		pane.add(botaoAdicionar);
		pane.add(botaoConsultar);
		pane.add(botaoAtualizar);
		pane.add(botaoDeletar);

		botaoAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String nome = textNome.getText();
					double preco = Double.parseDouble(textPreco.getText());
					int fabricanteID = Integer.parseInt(textFabricanteID.getText());
					int categoriaID = Integer.parseInt(textCategoriaID.getText());
					int produtoID = Integer.parseInt(textID.getText());

					if (Produto.adicionarProduto(nome, preco, fabricanteID, categoriaID, produtoID)) {
						JOptionPane.showMessageDialog(janelaProduto, "Produto adicionado com sucesso!");
					} else {
						JOptionPane.showMessageDialog(janelaProduto, "Erro ao adicionar produto.");
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(janelaProduto, "Por favor, insira valores válidos.");
				}
			}
		});

		botaoConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int produtoID = Integer.parseInt(textID.getText());
					Produto produto = Produto.obterProdutoPorID(produtoID);

					if (produto != null) {
						textNome.setText(produto.getNome());
						textPreco.setText(String.valueOf(produto.getPreco()));
						textFabricanteID.setText(String.valueOf(produto.getFabricanteID()));
						textCategoriaID.setText(String.valueOf(produto.getCategoriaID()));
					} else {
						JOptionPane.showMessageDialog(janelaProduto, "Produto não encontrado.");
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(janelaProduto, "Por favor, insira um ID válido.");
				}
			}
		});

		botaoAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int produtoID = Integer.parseInt(textID.getText());
					String nome = textNome.getText();
					double preco = Double.parseDouble(textPreco.getText());
					int fabricanteID = Integer.parseInt(textFabricanteID.getText());
					int categoriaID = Integer.parseInt(textCategoriaID.getText());

					Produto produto = new Produto(produtoID, nome, preco, fabricanteID, categoriaID);
					if (produto.atualizarProduto(nome, preco, fabricanteID, categoriaID, produtoID)) {
						JOptionPane.showMessageDialog(janelaProduto, "Produto atualizado com sucesso!");
					} else {
						JOptionPane.showMessageDialog(janelaProduto, "Erro ao atualizar produto.");
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(janelaProduto, "Por favor, insira valores válidos.");
				}
			}
		});

		botaoDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int produtoID = Integer.parseInt(textID.getText());
					Produto produto = new Produto();
					if (produto.deletarProduto(produtoID)) {
						JOptionPane.showMessageDialog(janelaProduto, "Produto deletado com sucesso!");
					} else {
						JOptionPane.showMessageDialog(janelaProduto, "Erro ao deletar produto.");
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(janelaProduto, "Por favor, insira um ID válido.");
				}
			}
		});

		return janelaProduto;
	}
}
