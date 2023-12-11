from Nodo import Nodo


class ListaEncadeada:
    def __init__(self, *nodos: "Nodo"):
        self.__primeiro: "Nodo" = None
        for nodo in nodos:
            self.inserir(nodo.get_valor())

    def get_primeiro(self):
        return self.__primeiro

    def inserir(self, valor: object):
        novo_nodo = Nodo(valor)
        if not self.__primeiro:
            self.__primeiro = novo_nodo
        else:
            atual = self.__primeiro
            anterior = None
            while atual:
                if atual.get_valor() == valor:
                    return
                anterior = atual
                atual = atual.get_proximo()
            anterior.set_proximo(novo_nodo)

    def busca(self, valor: object):
        atual = self.__primeiro
        while atual:
            if atual.get_valor() == valor:
                return atual
            atual = atual.get_proximo()
        return None

    def remove(self, valor: object):
        if not self.__primeiro:
            return
        if self.__primeiro.get_valor() == valor:
            self.__primeiro = self.__primeiro.get_proximo()
            return
        atual = self.__primeiro
        while atual.get_proximo():
            if atual.get_proximo().get_valor() == valor:
                atual.set_proximo(atual.get_proximo().get_proximo())
                return
            atual = atual.get_proximo()

    def remover_duplicatas(self):
        atual = self.__primeiro
        while atual:
            proximo = atual.get_proximo()
            while proximo and proximo.get_valor() == atual.get_valor():
                proximo = proximo.get_proximo()
            atual.set_proximo(proximo)
            atual = proximo

    def __str__(self):
        elementos = []
        atual = self.__primeiro
        while atual:
            elementos.append(str(atual.get_valor()))
            atual = atual.get_proximo()
        return f" → ".join(elementos) + " → None"


if __name__ == "__main__":
    nodo1 = Nodo(0)
    nodo2 = Nodo(1)
    nodo3 = Nodo(2)
    nodo4 = Nodo(3)
    nodo5 = Nodo(4)
    nodo6 = Nodo(1)
    nodo7 = Nodo(5)
    lista = ListaEncadeada(nodo1, nodo2, nodo3, nodo4, nodo5, nodo6, nodo7)
    print(f"Lista Encadeada: {lista}")
