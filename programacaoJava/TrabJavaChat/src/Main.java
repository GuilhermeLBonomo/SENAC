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
                System.err.println("Erro ao iniciar o servidor: " + e.getMessage());
                System.exit(-1);
            }
        });
        serverThread.start();

        // Aqui iniciamos a interface do cliente
        SwingUtilities.invokeLater(() -> {
            try {
                iniciarInterfaceCliente("127.0.0.1", PORTA);
            } catch (IOException e) {
                System.err.println("Erro ao iniciar a interface do cliente: " + e.getMessage());
            }
        });
    }

    private static void iniciarInterfaceCliente(String host, int porta) throws IOException {
        Socket clientSocket = new Socket(host, porta);
        System.out.println("Cliente conectado ao servidor!");

        // Inicializa a interface gráfica do cliente
        ClienteGUI clienteGUI = new ClienteGUI(clientSocket);
    }
}
