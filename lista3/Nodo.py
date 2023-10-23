class Nodo:
    def __init__(self, valor: object, proximo: "Nodo" = None):
        self.__valor: object = valor
        self.__proximo: "Nodo" = proximo

    def get_valor(self):
        return self.__valor

    def get_proximo(self):
        return self.__proximo

    def set_proximo(self, proximo: "Nodo"):
        self.__proximo = proximo
