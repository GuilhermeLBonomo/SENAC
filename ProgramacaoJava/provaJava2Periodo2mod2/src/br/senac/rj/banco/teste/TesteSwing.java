package br.senac.rj.banco.teste;

// Alunos: Guilherme Lopes, Breno Costa & Gabriel Da Cunha
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import br.senac.rj.banco.janelas.JanelaAdicionarFabricante;
import br.senac.rj.banco.janelas.JanelaAtualizarFabricante;
import br.senac.rj.banco.janelas.JanelaBuscarFabricante;
import br.senac.rj.banco.janelas.JanelaCategoria;
import br.senac.rj.banco.janelas.JanelaListaProdutosJava;
import br.senac.rj.banco.janelas.JanelaProduto;
import br.senac.rj.banco.janelas.JanelaRemoverFabricante;

public class TesteSwing {

	public static void apresentarMenu() {
		JFrame janelaPrincipal = new JFrame("Main");
		janelaPrincipal.setTitle("Main");
		janelaPrincipal.setResizable(false);
		janelaPrincipal.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		janelaPrincipal.setSize(400, 300);
		UIManager.put("OptionPane.yesButtonText", "Sim");
		UIManager.put("OptionPane.noButtonText", "Não");

		JMenuBar menuBar = new JMenuBar();
		janelaPrincipal.setJMenuBar(menuBar);

		JMenu menuAtualizar = new JMenu("CRUD: Fabricante");
		menuBar.add(menuAtualizar);

		JMenuItem menuBuscar = new JMenuItem("Buscar");
		JMenuItem menuCriar = new JMenuItem("Criar");
		JMenuItem menuAtualizarFabricante = new JMenuItem("Atualizar");
		JMenuItem menuDeletar = new JMenuItem("Deletar");

		menuAtualizar.add(menuBuscar);
		menuAtualizar.add(menuCriar);
		menuAtualizar.add(menuAtualizarFabricante);
		menuAtualizar.add(menuDeletar);

		JMenu menuCRUD = new JMenu("CRUD: Categoria");
		menuBar.add(menuCRUD);

		JMenu menuCategorias = new JMenu("Categorias");
		menuCRUD.add(menuCategorias);

		JMenuItem menuCategoria = new JMenuItem("Gerenciar Categorias");

		menuCategorias.add(menuCategoria);

		JMenuItem menuProdutos = new JMenuItem("CRUD: Produtos");
		menuBar.add(menuProdutos);

		menuBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JanelaBuscarFabricante.criarJanelaBuscarFabricante().setVisible(true);
			}
		});

		menuCriar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JanelaAdicionarFabricante.criarJanelaAdicionarFabricante().setVisible(true);
			}
		});

		menuAtualizarFabricante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JanelaAtualizarFabricante.criarJanelaAtualizarFabricante().setVisible(true);
			}
		});

		menuDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JanelaRemoverFabricante.criarJanelaRemoverFabricante().setVisible(true);
			}
		});

		menuCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JanelaCategoria.criarJanelaCategoria().setVisible(true);
			}
		});

		menuProdutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JanelaProduto.criarJanelaProduto().setVisible(true);
				JanelaListaProdutosJava.criarJanelaListaProdutos().setVisible(true);
			}
		});

		janelaPrincipal.setVisible(true);
	}

	public static void main(String[] args) {
		apresentarMenu();
	}
}
