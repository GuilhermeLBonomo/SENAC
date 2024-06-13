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

final public class ClienteGUI extends JFrame implements ActionListener {

    private JTextField inputTextField;
    private JTextArea chatArea;
    private JButton sendButton;
    private Socket clientSocket;
    private PrintStream outputStream;
    private String nome;

    private static final int MAX_MESSAGE_LENGTH = 2000;

    public ClienteGUI(Socket clientSocket, String nome) {
        this.clientSocket = clientSocket;
        this.nome = nome;

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

        initComponents();
    }

    private void initComponents() {
        setTitle("JavaZap Cliente - " + nome);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel contentPane = new JPanel(new BorderLayout());

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
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sendButton || e.getSource() == inputTextField) {
            sendMessage();
        }
    }

    private void sendMessage() {
        String message = inputTextField.getText().trim();

        if (message.isEmpty()) {
            return;
        }
        if (message.length() > MAX_MESSAGE_LENGTH) {
            JOptionPane.showMessageDialog(this,
                    "A mensagem excede o tamanho máximo permitido.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        message = String.format("%s|%s[%s]: %s", LocalDate.now(), LocalTime.now(), nome, message);
        chatArea.append(message + "\n");

        try {
            outputStream.println(message);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Erro ao enviar mensagem.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }

        inputTextField.setText("");
    }
}
