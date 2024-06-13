package Net;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

final public class Servidor implements BaseNet {
    private static List<PrintStream> clientesConectados = new ArrayList<>();

    @Override
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

    @Override
    public void iniciar() {
        iniciar(BaseNet.getHostPadrao(), BaseNet.getPortaPadrao());
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

    public Servidor() {}

    private static void broadcastMessage(String message) {
        for (PrintStream clientOutput : clientesConectados) {
            clientOutput.println(message);
        }
    }

    public static void main(String[] args) {
        Servidor s = new Servidor();
        s.iniciar();
    }
}
