package Net;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public final class Servidor implements BaseNet {
    private static List<PrintStream> clientesConectados = new ArrayList<>();

    private static final int MAX_MESSAGE_LENGTH = 2000;
    private static final int MAX_NAME_LENGTH = 5;

    public void iniciar() {
        iniciar(BaseNet.getHostPadrao(), BaseNet.getPortaPadrao());
    }

    public void iniciar(final String host, final int porta) {
        try (ServerSocket serverSocket = new ServerSocket(porta)) {
            System.out.printf("Servidor iniciado em %s:%d\n", host, porta);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.printf("Novo cliente conectado: %s\n", clientSocket);

                PrintStream outputStream = new PrintStream(clientSocket.getOutputStream(), true);
                clientesConectados.add(outputStream);

                Thread thread = new Thread(new TratarCliente(clientSocket, outputStream));
                thread.start();
            }
        } catch (IOException e) {
            System.err.printf("Erro ao iniciar o servidor: %s\n", e.getMessage());
        }
    }

    static class TratarCliente implements Runnable {
        private Socket clientSocket;
        private PrintStream outputStream;

        public TratarCliente(Socket clientSocket, PrintStream outputStream) {
            this.clientSocket = clientSocket;
            this.outputStream = outputStream;
        }

        @Override
        public void run() {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                String message;
                while ((message = reader.readLine()) != null) {
                    System.out.printf("Mensagem recebida: %s\n", message);

                    // Verifica e ajusta o tamanho da mensagem recebida
                    if (message.length() > MAX_MESSAGE_LENGTH) {
                        message = message.substring(0, MAX_MESSAGE_LENGTH);
                    }

                    // Envia mensagem para todos os clientes conectados
                    broadcastMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                clientesConectados.remove(outputStream);
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void broadcastMessage(String message) {
        for (PrintStream clientOutput : clientesConectados) {
            clientOutput.println(message);
        }
    }

    public static void main(String[] args) {
        Servidor servidor = new Servidor();
        servidor.iniciar();
    }
}
