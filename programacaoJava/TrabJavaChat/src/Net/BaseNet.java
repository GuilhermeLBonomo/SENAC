package Net;

public interface BaseNet {
    final String HOST_PADRAO = "localhost";
    final int PORTA_PADRAO = 12000;
    String host = HOST_PADRAO;
    int porta = PORTA_PADRAO;

    void iniciar(String host, int porta);
    void iniciar();

    static String getHostPadrao() {
        return HOST_PADRAO;
    }

    static int getPortaPadrao() {
        return PORTA_PADRAO;
    }
}
