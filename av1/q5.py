# Um estudante de redes recebeu o seguinte desafio:
# Crie um sistema para gerenciar requisições de um servidor
# O primeiro a enviar a requisição deve ser o primeiro a receber a resposta
# requisição = (ip_servidor, GET)
# resposta = (ip_cliente, POST)


# Tive essa ideia ao fazer testes de capturas de pacotes com wireshark no metasploitable e kali linux

from queue import Queue


class Requisicao:
    """Classe que simula a requisição."""

    def __init__(self, ip: str, metodo: str) -> None:
        """Construtor que recebe um ip e um método http."""
        # Por ser um exemplo não há testes se os métodos e ip são válidos.
        self.__ip = ip
        self.__metodo = metodo

    def get_ip(self) -> str:
        """getter para o ip"""
        return self.__ip

    def get_metodo(self) -> str:
        """getter para o método http"""
        return self.__metodo


class Servidor:
    """Classe que simula o servidor"""

    def __init__(self) -> None:
        """Construtor que inicia o servidor e prepara as queues de requisições.
        São usadas queues por que"""
        print("Iniciando o Servidor...\n")
        self.requisicoes: Queue = Queue()
        self.respostas: Queue = Queue()

    def receber_requisicao(self, requisicao: Requisicao) -> None:
        """Função que recebe a requisição e adiciona ao fim da lista."""
        ip_servidor = requisicao.get_ip()
        metodo = requisicao.get_metodo()
        print(f"Recebendo requisição de: {ip_servidor} - {metodo}")
        self.requisicoes.put(requisicao)

    def processar_requisicoes(self) -> None:
        """Funcão que processa as requisções da primeira a chegar até"""
        while not self.requisicoes.empty():
            requisicao = self.requisicoes.get()
            ip_servidor = requisicao.get_ip()
            metodo = requisicao.get_metodo()
            resposta = Requisicao(ip_servidor, "POST")
            self.respostas.put(resposta)
            print(f"Processando requisição de: {ip_servidor} - {metodo}")

    def enviar_respostas(self) -> None:
        """Função que envia as respostas http após receber as requisições."""
        while not self.respostas.empty():
            resposta = self.respostas.get()
            ip_cliente = resposta.get_ip()
            metodo = resposta.get_metodo()
            print(f"Enviando resposta para: {ip_cliente} - {metodo}")


def main() -> None:
    """Exemplos"""
    servidor = Servidor()
    servidor.receber_requisicao(Requisicao("192.168.0.1", "GET"))
    servidor.receber_requisicao(Requisicao("192.168.0.2", "GET"))
    servidor.receber_requisicao(Requisicao("192.168.0.3", "GET"))
    servidor.processar_requisicoes()
    servidor.enviar_respostas()


if __name__ == "__main__":
    main()
