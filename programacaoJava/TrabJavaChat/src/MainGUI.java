import InterfacesGraficas.ClienteGUI;
import Net.BaseNet;
import Net.Servidor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;

final public class MainGUI extends JFrame {

    private JTextField portaServidorField;
    private JButton iniciarServidorButton;

    public MainGUI() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Configuração Inicial - Porta do Servidor");
        setSize(400, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel contentPane = new JPanel(new GridLayout(2, 1));
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel portaPanel = new JPanel(new BorderLayout());
        JLabel portaLabel = new JLabel("Porta do Servidor:");
        portaServidorField = new JTextField("12000");
        portaPanel.add(portaLabel, BorderLayout.WEST);
        portaPanel.add(portaServidorField, BorderLayout.CENTER);

        iniciarServidorButton = new JButton("Iniciar Servidor");
        iniciarServidorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String portaStr = portaServidorField.getText().trim();

                int porta;
                try {
                    porta = Integer.parseInt(portaStr);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(MainGUI.this, "Porta inválida.");
                    return;
                }
                Thread servidorThread = new Thread(() -> {
                    Servidor servidor = new Servidor();
                    servidor.iniciar(BaseNet.getHostPadrao(), porta);
                });
                servidorThread.start();
                dispose();
                configurarClienteGUI();
            }
        });

        contentPane.add(portaPanel);
        contentPane.add(iniciarServidorButton);

        setContentPane(contentPane);
        setVisible(true);
    }

    private void configurarClienteGUI() {
        JFrame configuracaoClienteFrame = new JFrame("Configuração do Cliente");
        configuracaoClienteFrame.setSize(400, 200);
        configuracaoClienteFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel contentPane = new JPanel(new GridLayout(4, 2));
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel nomeLabel = new JLabel("Nome:");
        JTextField nomeField = new JTextField();
        contentPane.add(nomeLabel);
        contentPane.add(nomeField);

        JLabel hostLabel = new JLabel("Host do Cliente:");
        JTextField hostField = new JTextField("localhost");
        contentPane.add(hostLabel);
        contentPane.add(hostField);

        JLabel portaLabel = new JLabel("Porta do Cliente:");
        JTextField portaField = new JTextField("12000");
        contentPane.add(portaLabel);
        contentPane.add(portaField);

        JButton iniciarClienteButton = new JButton("Iniciar Cliente");
        iniciarClienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = nomeField.getText().trim();
                String host = hostField.getText().trim();
                String portaStr = portaField.getText().trim();

                int porta;
                try {
                    porta = Integer.parseInt(portaStr);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(configuracaoClienteFrame, "Porta inválida.");
                    return;
                }
                SwingUtilities.invokeLater(() -> {
                    Socket clientSocket = null;
                    try {
                        clientSocket = new Socket(host, porta);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(configuracaoClienteFrame, "Erro ao conectar ao servidor.");
                        return;
                    }
                    new ClienteGUI(clientSocket, nome);
                    configuracaoClienteFrame.dispose();
                });
            }
        });

        contentPane.add(new JLabel()); // espaço vazio
        contentPane.add(iniciarClienteButton);

        configuracaoClienteFrame.setContentPane(contentPane);
        configuracaoClienteFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainGUI());
    }
}
