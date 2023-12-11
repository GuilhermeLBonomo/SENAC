class Nodo:
    def __init__(self, data):
        self.__data = data
        self.__next = None

    def get_data(self):
        return self.__data

    def set_data(self, data):
        self.__data = data

    def get_next(self):
        return self.__next

    def set_next(self, next_node):
        self.__next = next_node


class ListaCircular:
    def __init__(self):
        self.__head = None

    def get_head(self):
        return self.__head

    def inserir(self, data):
        novo_nodo = Nodo(data)
        if not self.__head:
            self.__head = novo_nodo
            novo_nodo.set_next(self.__head)
        else:
            atual = self.__head
            while atual.get_next() != self.__head:
                atual = atual.get_next()
            atual.set_next(novo_nodo)
            novo_nodo.set_next(self.__head)

    def remover(self, data):
        if not self.__head:
            return
        atual = self.__head
        anterior = None
        while atual:
            if atual.get_data() == data:
                if atual == self.__head:
                    temp = atual
                    while temp.get_next() != self.__head:
                        temp = temp.get_next()
                    temp.set_next(self.__head.get_next())
                    self.__head = self.__head.get_next()
                else:
                    anterior.set_next(atual.get_next())
                return
            anterior = atual
            atual = atual.get_next()
            if atual == self.__head:
                break

    def busca(self, data):
        if not self.__head:
            return False
        atual = self.__head
        while atual:
            if atual.get_data() == data:
                return True
            atual = atual.get_next()
            if atual == self.__head:
                break
        return False

    def __str__(self):
        out = ""
        if not self.__head:
            pass
        else:
            nodo_atual = self.__head
            while True:
                out += f"{nodo_atual.get_data()} -> "
                nodo_atual = nodo_atual.get_next()
                if nodo_atual == self.__head:
                    break
        out += "None..."
        return out


if __name__ == "__main__":
    lista_c = ListaCircular()
    lista_c.inserir(1)
    lista_c.inserir(2)
    lista_c.inserir(3)
    lista_c.inserir(4)
    lista_c.inserir(5)
    print(f"Lista circular: \n{lista_c}")
    lista_c.remover(3)
    print(f"Lista após remover o elemento 3: {lista_c}")
    print("O elemento 2 está na lista? ", lista_c.busca(2))
    print("O elemento 6 está na lista? ", lista_c.busca(6))
