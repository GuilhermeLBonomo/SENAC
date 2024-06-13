package InterfacesGraficas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

import Net.BaseNet;

public class ClienteGUI extends JFrame implements ActionListener {

    private JTextField inputTextField;
    private JTextArea chatArea;
    private JButton sendButton;
    private Socket clientSocket;
    private PrintStream outputStream;
    private String clientId;
    private boolean primeiraMensagem = true;
    private String nome = "";

    private static final int MAX_MESSAGE_LENGTH = 2000;
    private static final int MAX_NAME_LENGTH = 50;

    public ClienteGUI(Socket clientSocket) {
        this.clientSocket = clientSocket;

        try {
            outputStream = new PrintStream(clientSocket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Erro ao inicializar o fluxo de saída.");
            return;
        }

        if (outputStream == null) {
            System.err.println("Erro: fluxo de saída não inicializado corretamente.");
            return;
        }

        clientId = UUID.randomUUID().toString();
        initComponents();
    }

    private void initComponents() {
        setTitle("JavaZap Cliente");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setBackground(Color.LIGHT_GRAY);

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(chatArea);
        contentPane.add(scrollPane, BorderLayout.CENTER);

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

        setContentPane(contentPane);
        setVisible(true);
        inputTextField.requestFocus();

        JOptionPane.showMessageDialog(this,
                "Digite seu nome de usuário com exatamente 5 caracteres.");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sendButton || e.getSource() == inputTextField) {
            sendMessage();
        }
    }

    private void sendMessage() {
        String message = inputTextField.getText().trim();

        if (primeiraMensagem) {
            if (message.length() > MAX_NAME_LENGTH) {
                nome = message;
                primeiraMensagem = false;
                inputTextField.setText(""); // Limpa o campo de texto após definir o nome de usuário
                return; // Retorna sem enviar a mensagem para evitar exibir no chat
            } else {
                JOptionPane.showMessageDialog(this,
                        "Nome de usuário deve ter exatamente 5 caracteres.",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
                return; // Retorna sem enviar a mensagem
            }
        }

        if (!message.isEmpty()) {
            // Limita o tamanho da mensagem
            if (message.length() > MAX_MESSAGE_LENGTH) {
                message = message.substring(0, MAX_MESSAGE_LENGTH);
            }
            message = String.format("%s|%s[%s]: %s", LocalDate.now(), LocalTime.now(), nome, message);
            chatArea.append(message + "\n");
            try {
                outputStream.println(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        inputTextField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Socket clientSocket = null;
            try {
                clientSocket = new Socket(BaseNet.getHostPadrao(), BaseNet.getPortaPadrao());
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Erro ao conectar ao servidor.");
            }
            new ClienteGUI(clientSocket);
        });
    }
}
