package Net;

import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Servidor {
    private int port;
    private List<PrintStream> clientOutputStreams;

    public Servidor(int port) {
        this.port = port;
        clientOutputStreams = new ArrayList<>();
        iniciar();
    }

    private void iniciar() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            InetAddress addr = InetAddress.getByName("10.136.64.191");
            System.out.println("Servidor multithread iniciado na porta: " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado: " + clientSocket.getInetAddress().getHostAddress());

                Scanner input = new Scanner(clientSocket.getInputStream());
                String clientId = input.nextLine();

                PrintStream clientOutput = new PrintStream(clientSocket.getOutputStream());
                clientOutputStreams.add(clientOutput);

                new Thread(() -> {
                    try {
                        Scanner inputClient = new Scanner(clientSocket.getInputStream());

                        while (true) {
                            if (inputClient.hasNextLine()) {
                                String message = inputClient.nextLine();
                                System.out.println("Cliente " + clientId + ": " + message);

                                // Envia a mensagem para todos os clientes conectados
                                for (PrintStream output : clientOutputStreams) {
                                    output.println(clientId + ": " + message);
                                }
                            }
                        }
                    } catch (IOException e) {
                        System.err.println("Erro na comunicação com o cliente " + clientId + ": " + e.getMessage());
                    } finally {
                        // Remove o fluxo de saída do cliente da lista
                        clientOutputStreams.remove(clientOutput);
                        try {
                            clientSocket.close();
                        } catch (IOException e) {
                            System.err.println("Erro ao fechar a conexão com o cliente " + clientId + ": " + e.getMessage());
                        }
                    }
                }).start();
            }
        } catch (IOException e) {
            System.err.println("Erro ao iniciar o servidor: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Servidor(10000);
    }
}
