import InterfacesGraficas.ClienteGUI;
import Net.Cliente;
import Net.Servidor;

import javax.swing.*;
import java.io.IOException;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        final int PORTA = 10000;
        Thread serverThread = new Thread(() -> {
            try {
                new Servidor(PORTA);
            } catch (Exception e) {
                System.err.printf("Erro ao iniciar o servidor: %s\n", e.getMessage());
                System.exit(-1);
            }
        });
        serverThread.start();
        SwingUtilities.invokeLater(() -> {
            try {
                iniciarInterfaceCliente("127.0.0.1", PORTA);
            } catch (IOException e) {
                System.err.printf("Erro ao iniciar a interface do cliente: %s\n", e.getMessage());
            }
        });
    }

    private static void iniciarInterfaceCliente(String host, int porta) throws IOException {
        Socket clientSocket = new Socket(host, porta);
        System.out.println("Cliente conectado ao servidor!");
        ClienteGUI clienteGUI = new ClienteGUI(clientSocket);
    }
}
