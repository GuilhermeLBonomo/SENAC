package br.senac.rj.banco.janelas;

//Alunos: Guilherme Lopes, Breno Costa & Gabriel Da Cunha
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import br.senac.rj.banco.modelo.Produto;

public final class JanelaListaProdutosJava {
	private static DefaultTableModel model;
	private static JTable table;

	public static JFrame criarJanelaListaProdutos() {
		JFrame janela = new JFrame("Lista de Produtos");
		janela.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		janela.setSize(600, 400);
		janela.setLocation(1000, 250);

		JPanel painelSuperior = new JPanel();
		painelSuperior.setPreferredSize(new Dimension(1, 20));

		model = new DefaultTableModel();
		table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);

		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atualizarListaProdutos();
			}
		});

		JPanel painelBotoes = new JPanel();
		painelBotoes.add(btnAtualizar);

		janela.setLayout(new BorderLayout());
		janela.add(painelSuperior, BorderLayout.NORTH);
		janela.add(scrollPane, BorderLayout.CENTER);
		janela.add(painelBotoes, BorderLayout.SOUTH);

		return janela;
	}

	public static void atualizarListaProdutos() {
		model.setRowCount(0);

		String[] colunas = { "ID", "Nome", "Preço", "Fabricante", "Categoria" };
		model.setColumnIdentifiers(colunas);

		table.setRowHeight(20);

		TableColumnModel columnModel = table.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(50);
		columnModel.getColumn(1).setPreferredWidth(150);
		columnModel.getColumn(2).setPreferredWidth(80);
		columnModel.getColumn(3).setPreferredWidth(100);
		columnModel.getColumn(4).setPreferredWidth(100);

		try {
			List<Produto> produtos = Produto.obterProdutosOrdenados();
			for (Produto produto : produtos) {
				Object[] linha = { produto.getProdutoID(), produto.getNome(), produto.getPreco(),
						produto.obterNomeFabricante(), produto.obterNomeCategoria() };
				model.addRow(linha);
			}
		} catch (Exception error) {
			System.out.println("Erro ao consultar os produtos: " + error.toString());
		}
	}
}