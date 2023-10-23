from Nodo import Nodo


class Pilha:
    def __init__(self, *valores: object):
        self.__topo: Nodo = None
        for valor in valores:
            self.empilhar(valor)

    def esta_vazia(self) -> bool:
        return self.__topo is None

    def empilhar(self, valor: object) -> None:
        novo_nodo = Nodo(valor)
        novo_nodo.set_proximo(self.__topo)
        self.__topo = novo_nodo

    def desempilhar(self) -> object:
        if self.esta_vazia():
            return None
        valor = self.__topo.get_valor()
        self.__topo = self.__topo.get_proximo()
        return valor

    def obter_topo(self) -> object:
        if self.esta_vazia():
            return None
        return self.__topo.get_valor()

    def maior_elemento_na_pilha(self) -> object:
        if self.esta_vazia():
            return None

        maior = self.__topo.get_valor()
        atual = self.__topo.get_proximo()

        while atual:
            if atual.get_valor() > maior:
                maior = atual.get_valor()
            atual = atual.get_proximo()

        return maior

    def imprimir_pilha_inversa(self) -> None:
        pilha_inversa = Pilha()

        while not self.esta_vazia():
            valor = self.desempilhar()
            pilha_inversa.empilhar(valor)

        while not pilha_inversa.esta_vazia():
            valor = pilha_inversa.desempilhar()
            print(valor, end=" ")

    def pilhas_sao_iguais(self, pilha2: "Pilha") -> bool:
        pilha1 = Pilha()

        while not self.esta_vazia() and not pilha2.esta_vazia():
            valor1 = self.desempilhar()
            valor2 = pilha2.desempilhar()
            if valor1 != valor2:
                return False
            pilha1.empilhar(valor1)
            pilha2.empilhar(valor2)

        return self.esta_vazia() and pilha2.esta_vazia()

    def sequencia_carros(self, placas: list, placa_alvo: object) -> list:
        sequencia = []

        for placa in placas:
            self.empilhar(placa)
            if placa == placa_alvo:
                while not self.esta_vazia() and self.obter_topo() != placa_alvo:
                    sequencia.append(self.desempilhar())
                return sequencia

        return sequencia

    def TPilha(self, vetor: list) -> None:
        for numero in vetor:
            if numero % 2 == 0:
                self.empilhar(numero)
            else:
                self.desempilhar()

    def TPilha2(self, pilha_N: "Pilha", pilha_P: "Pilha", vetor: list) -> None:
        for numero in vetor:
            if numero > 0:
                pilha_P.empilhar(numero)
            elif numero < 0:
                pilha_N.empilhar(numero)
            else:
                pilha_N.desempilhar()
                pilha_P.desempilhar()


if __name__ == "__main__":
    pilha = Pilha(1, 2, 3)
    print(f"Elemento no topo: {pilha.obter_topo()}")
    print(f"Removendo elemento do topo: {pilha.desempilhar()}")
    print(f"A pilha está vazia? {pilha.esta_vazia()}")
    pilha_teste = Pilha(5, 10, 3)
    print(f"Maior elemento na pilha de teste: {pilha.maior_elemento_na_pilha()}")
    pilha_teste2 = Pilha(1, 2, 3)
    print("Imprimindo a pilha de teste2 em ordem inversa: ")
    pilha.imprimir_pilha_inversa()
    pilha1 = Pilha(1, 2, 3)
    pilha2 = Pilha(1, 2, 4)
    print(f"As pilhas 1 e 2 são iguais? {pilha.pilhas_sao_iguais(pilha2)}")
