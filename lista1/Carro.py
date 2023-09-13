class Carro:
    def __init__(self, consumo) -> None:
        self.__consumo = consumo
        self.__combustivel = 0.0
    

    def andar(self, distancia) -> None:
        try:
            x = float(distancia)/self.__consumo
            if x < 0:
                raise ValueError(f"Erro: Valor {distancia} inválido.")
        except TypeError as e:
            raise ValueError(f"Erro: Valor {distancia} inválido.") from e
        if(self.__combustivel - x <= 0):
            self.__combustivel = 0
        else:
            self.__combustivel -= x
    
    def adicionar_gasolina(self, valor) -> None:
        try:
            x = float(valor)
            if x <= 0:
                raise ValueError(f"Erro: Valor {valor} inválido.")
        except TypeError as e:
            raise ValueError(f"Erro: Valor {valor} inválido.") from e
        self.__combustivel += x
    
    def obterGasolina(self) -> None:
        print(self.__combustivel)

if __name__ == '__main__':
    meuFusca = Carro(15)
    meuFusca.adicionar_gasolina(20)
    meuFusca.obterGasolina()
    meuFusca.andar(100)
    meuFusca.obterGasolina()