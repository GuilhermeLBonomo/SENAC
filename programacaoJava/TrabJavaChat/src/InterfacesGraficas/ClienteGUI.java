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

public class ClienteGUI extends JFrame implements ActionListener {

    private JTextField inputTextField;
    private boolean primeiraMensagem;
    private JTextArea chatArea;
    private JButton sendButton;
    private Socket clientSocket;
    private PrintStream outputStream;
    private String clientId;
    private boolean firstMessage = true;
    private String nome = "";

    public ClienteGUI(Socket clientSocket) {
        this.clientSocket = clientSocket;
        primeiraMensagem = true;

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
        if (primeiraMensagem){
            nome = message;
            primeiraMensagem = false;
        }
        if (!message.isEmpty()) {
                message = String.format("%s|%s[%s]: %s\n", LocalDate.now(), LocalTime.now(),nome, message);
                chatArea.append(message);
                try {
                    outputStream.println(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            inputTextField.setText("");
        }
    }


