package br.senac.rj.banco.janelas;

//Alunos: Guilherme Lopes, Breno Costa & Gabriel Da Cunha
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import br.senac.rj.banco.modelo.Fabricante;

public final class JanelaBuscarFabricante {
	public static JFrame criarJanelaBuscarFabricante() {
		JFrame janelaFabricante = new JFrame("Buscar Fabricante");
		janelaFabricante.setResizable(false);
		janelaFabricante.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		janelaFabricante.setSize(400, 300);

		Container caixa = janelaFabricante.getContentPane();
		caixa.setLayout(null);

		JLabel labelFabricanteID = new JLabel("ID do Fabricante: ");
		JLabel labelNome = new JLabel("Nome: ");

		labelFabricanteID.setBounds(50, 40, 100, 20);
		labelNome.setBounds(50, 80, 100, 20);

		JTextField jTextFabricanteID = new JTextField();
		JTextField jTextNome = new JTextField();
		JTextField jTextLocalizacao = new JTextField();
		JTextField jTextDescricao = new JTextField();

		jTextNome = new JTextField();
		jTextLocalizacao = new JTextField();
		jTextDescricao = new JTextField();

		jTextFabricanteID.setEnabled(true);
		jTextNome.setEnabled(true);
		jTextLocalizacao.setEnabled(true);
		jTextDescricao.setEnabled(true);

		jTextFabricanteID.setBounds(180, 40, 50, 20);
		jTextNome.setBounds(180, 80, 150, 20);
		jTextLocalizacao.setBounds(180, 120, 150, 20);
		jTextDescricao.setBounds(180, 160, 150, 20);

		JRadioButton radioID = new JRadioButton("Buscar por ID");
		JRadioButton radioNome = new JRadioButton("Buscar por Nome");
		radioID.setSelected(true);

		radioID.setBounds(50, 120, 150, 20);
		radioNome.setBounds(200, 120, 150, 20);

		JButton botaoConsultar = new JButton("Consultar");
		botaoConsultar.setBounds(50, 160, 150, 20);
		janelaFabricante.add(botaoConsultar);

		JTextField jTextNomeFinal = jTextNome;
		ActionListener consultarListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Fabricante fabricante = new Fabricante();
					if (radioID.isSelected()) {
						int fabricanteID = Integer.parseInt(jTextFabricanteID.getText());
						if (fabricante.consultarFabricante(fabricanteID)) {
							exibirInformacoesFabricante(fabricante);
						} else {
							JOptionPane.showMessageDialog(janelaFabricante, "Fabricante não encontrado!");
						}
					} else if (radioNome.isSelected()) {
						String nome = jTextNomeFinal.getText().trim();
						Fabricante fabricanteEncontrado = fabricante.consultarFabricantePorNome(nome);
						if (fabricanteEncontrado != null) {
							exibirInformacoesFabricante(fabricanteEncontrado);
						} else {
							JOptionPane.showMessageDialog(janelaFabricante, "Fabricante não encontrado!");
						}
					} else {
						JOptionPane.showMessageDialog(janelaFabricante,
								"Selecione uma opção de busca (ID ou Nome) antes de consultar!");
					}
				} catch (Exception erro) {
					JOptionPane.showMessageDialog(janelaFabricante, "Erro: " + erro.getMessage());
				}
			}
		};

		botaoConsultar.addActionListener(consultarListener);

		janelaFabricante.add(labelFabricanteID);
		janelaFabricante.add(labelNome);
		janelaFabricante.add(jTextFabricanteID);
		janelaFabricante.add(jTextNome);
		janelaFabricante.add(radioID);
		janelaFabricante.add(radioNome);

		return janelaFabricante;
	}

	private static void exibirInformacoesFabricante(Fabricante fabricante) {
		JFrame janelaInformacoes = new JFrame("Informações do Fabricante");
		janelaInformacoes.setSize(400, 250);

		JLabel labelID = new JLabel("ID: " + fabricante.getFabricanteID());
		JLabel labelNome = new JLabel("Nome: " + fabricante.getNome());
		JLabel labelLocalizacao = new JLabel("Localização: " + fabricante.getLocalizacao());
		JLabel labelDescricao = new JLabel("Descrição: " + fabricante.getDescricao());

		labelID.setBounds(50, 40, 200, 20);
		labelNome.setBounds(50, 80, 200, 20);
		labelLocalizacao.setBounds(50, 120, 200, 20);
		labelDescricao.setBounds(50, 160, 300, 20);

		janelaInformacoes.add(labelID);
		janelaInformacoes.add(labelNome);
		janelaInformacoes.add(labelLocalizacao);
		janelaInformacoes.add(labelDescricao);

		janelaInformacoes.setLayout(null);
		janelaInformacoes.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		janelaInformacoes.setVisible(true);
	}
}
