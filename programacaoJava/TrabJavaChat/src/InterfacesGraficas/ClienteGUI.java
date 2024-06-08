package InterfacesGraficas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.UUID;

public class ClienteGUI extends JFrame implements ActionListener {

    private JTextField inputTextField;
    private JTextArea chatArea;
    private JButton sendButton;
    private Socket clientSocket;
    private PrintStream outputStream;
    private String clientId; // Armazena o identificador do cliente
    private boolean firstMessage = true;

    public ClienteGUI(Socket clientSocket) {
        this.clientSocket = clientSocket;

        try {
            outputStream = new PrintStream(clientSocket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Erro ao inicializar o fluxo de saída.");
            return; // Encerra a execução do construtor se houver erro
        }

        if (outputStream == null) {
            System.err.println("Erro: fluxo de saída não inicializado corretamente.");
            return; // Encerra a execução do construtor se o outputStream for nulo
        }

        // Envie o identificador do cliente para o servidor
        clientId = UUID.randomUUID().toString();

        // Configurar a interface gráfica
        initComponents();
    }

    private void initComponents() {
        setTitle("JavaZap Cliente");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Criando o contentPane e definindo sua cor de fundo
        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setBackground(Color.LIGHT_GRAY);

        // Mostra as Mensagens
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(chatArea);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        // Caixa de texto de entrada + botão
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputTextField = new JTextField();
        sendButton = new JButton("Enviar");
        sendButton.setBackground(Color.BLUE);
        sendButton.setForeground(Color.WHITE);
        sendButton.addActionListener(this);
        inputTextField.addActionListener(this);
        inputPanel.add(inputTextField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);
        contentPane.add(inputPanel, BorderLayout.SOUTH);

        // Adicione o contentPane à janela
        setContentPane(contentPane);
        setVisible(true);
        inputTextField.requestFocus(); // Coloca o foco no campo de texto

        // Mostra uma mensagem explicativa sobre o primeiro uso
        JOptionPane.showMessageDialog(this,
                "Digite sua primeira mensagem para definir seu nome de usuário.");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sendButton || e.getSource() == inputTextField) {
            sendMessage();
        }
    }

    private void sendMessage() {
        String message = inputTextField.getText().trim();
        String nome = "";
        if (!message.isEmpty()) {

                chatArea.append(nome + ": " + message + "\n");
                try {
                    outputStream.println(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            inputTextField.setText("");
        }
    }


