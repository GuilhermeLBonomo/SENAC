from Nodo import Nodo


class Processo:
    def __init__(self, pid: int, nome: str, tempo_espera: int):
        self.__pid: int = pid
        self.__nome: str = nome
        self.__tempo_espera: int = tempo_espera

    def get_pid(self) -> int:
        return self.__pid

    def get_nome(self) -> str:
        return self.__nome

    def get_tempo_espera(self) -> int:
        return self.__tempo_espera

    def set_pid(self, pid: int) -> None:
        self.__pid = pid

    def set_nome(self, nome: str) -> None:
        self.__nome = nome

    def set_tempo_espera(self, tempo_espera: int) -> None:
        self.__tempo_espera = tempo_espera


class Queue:
    def __init__(self):
        self.__front = None
        self.__rear = None

    def is_empty(self) -> bool:
        return self.__front is None

    def push(self, processo):
        new_node = Nodo(processo)
        if self.__rear is None:
            self.__front = self.__rear = new_node
        else:
            self.__rear.set_proximo(new_node)
            self.__rear = new_node

    def pop(self):
        if self.is_empty():
            return None
        processo = self.__front.get_valor()
        self.__front = self.__front.get_proximo()
        if self.__front is None:
            self.__rear = None
        return processo

    def search(self, pid: int) -> int:
        current = self.__front
        index = 0
        while current:
            processo = current.get_valor()
            if processo.get_pid() == pid:
                return index
            current = current.get_proximo()
            index += 1
        return -1

    def print_queue(self):
        current = self.__front
        while current:
            processo = current.get_valor()
            print(
                f"PID: {processo.get_pid()}, Nome: {processo.get_nome()}, Tempo de Espera: {processo.get_tempo_espera()}"
            )
            current = current.get_proximo()

    def reverse(self):
        prev = None
        current = self.__front
        while current:
            next_node = current.get_proximo()
            current.set_proximo(prev)
            prev = current
            current = next_node
        self.__front, self.__rear = self.__rear, self.__front

    def get_front(self):
        return self.__front

    def get_rear(self):
        return self.__rear

    def set_front(self, front):
        self.__front = front

    def set_rear(self, rear):
        self.__rear = rear


if __name__ == "__main__":
    fila_processos = Queue()
    fila_processos.push(Processo(1, "Processo A", 5))
    fila_processos.push(Processo(2, "Processo B", 3))
    fila_processos.push(Processo(3, "Processo C", 7))
    print("Fila de Processos:")
    fila_processos.print_queue()

    MAX_TEMPO_ESPERA = 0
    MAX_TEMPO_PID = -1

    current = fila_processos.get_front()
    while current:
        processo = current.get_valor()
        if processo.get_tempo_espera() > MAX_TEMPO_ESPERA:
            MAX_TEMPO_ESPERA = processo.get_tempo_espera()
            MAX_TEMPO_PID = processo.get_pid()
        current = current.get_proximo()

    if MAX_TEMPO_PID != -1:
        print(f"Matando o processo com PID {MAX_TEMPO_PID}")
        index = fila_processos.search(MAX_TEMPO_PID)
        if index != -1:
            current = fila_processos.get_front()
            prev = None
            for _ in range(index):
                prev = current
                current = current.get_proximo()
            if prev is not None:
                prev.set_proximo(current.get_proximo())
            else:
                fila_processos.set_front(current.get_proximo())
            if current == fila_processos.get_rear():
                fila_processos.set_rear(prev)
    processo_executado = fila_processos.pop()
    if processo_executado:
        print(f"Executando processo com PID {processo_executado.get_pid()}")
    print("Fila de Processos Após a Execução:")
    fila_processos.print_queue()
