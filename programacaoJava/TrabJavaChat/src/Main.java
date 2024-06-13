import InterfacesGraficas.ClienteGUI;
import Net.BaseNet;
import Net.Servidor;

import javax.swing.*;
import java.net.Socket;

final public class Main {

    public static void main(String[] args) {
        // Iniciar o servidor em uma thread separada
        Thread servidorThread = new Thread(() -> {
            Servidor servidor = new Servidor();
            servidor.iniciar(BaseNet.getHostPadrao(), BaseNet.getPortaPadrao());
        });
        servidorThread.start();

        // Iniciar o cliente GUI na thread principal da interface gráfica
        SwingUtilities.invokeLater(() -> {
            Socket clientSocket;
            try {
                clientSocket = new Socket(BaseNet.getHostPadrao(), BaseNet.getPortaPadrao());
                new ClienteGUI(clientSocket);
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Erro ao conectar ao servidor.");
            }
        });
    }
}
