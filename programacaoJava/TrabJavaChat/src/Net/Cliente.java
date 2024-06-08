package Net;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.UUID;

public class Cliente {
    private static final String HOST_PADRAO = "127.0.0.1";
    private static final int PORTA_PADRAO = 10000;

    public static void main(String[] args) {
        String host = HOST_PADRAO;
        int porta = PORTA_PADRAO;

        if (args.length > 0) {
            host = args[0];
            if (args.length > 1) {
                try {
                    porta = Integer.parseInt(args[1]);
                } catch (NumberFormatException e) {
                    System.err.println("Porta inválida. Usando a porta padrão: " + PORTA_PADRAO);
                }
            }
        }
        run(host, porta);
    }

    public static void run(String host, int porta) {
        try (Socket clientSocket = new Socket(host, porta)) {
            System.out.println("Cliente conectado ao servidor!");

            // Gera um identificador único para o cliente
            String clientId = UUID.randomUUID().toString();

            // Envia o identificador para o servidor
            PrintStream output = new PrintStream(clientSocket.getOutputStream());
            output.println(clientId);

            // Cria uma thread para receber mensagens do servidor
            new Thread(() -> {
                try {
                    Scanner input = new Scanner(clientSocket.getInputStream());
                    while (input.hasNextLine()) {
                        String message = input.nextLine();
                        System.out.println(message);
                    }
                } catch (IOException e) {
                    System.err.println("Erro na comunicação com o servidor: " + e.getMessage());
                }
            }).start();

            // Agora o cliente pode enviar mensagens para o servidor
            Scanner scanner = new Scanner(System.in);
            while (true) {
                String message = scanner.nextLine();
                output.println(message);
            }
        } catch (IOException e) {
            System.err.println("Erro ao conectar ao servidor: " + e.getMessage());
        }
    }
}
