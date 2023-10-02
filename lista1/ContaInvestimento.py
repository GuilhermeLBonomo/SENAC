from Conta_Corrente import Conta_Corrente

class contaInvestimento(Conta_Corrente):
    def __init__(self, numero: int, taxaJuros: float, nome: str, saldo=0) -> None:
        super().__init__(numero, nome, saldo)
        self.__taxaJuros = taxaJuros
        
    
    def adicioneJuros(self, valor=0.10) -> None:
        try:
            x = float(valor)
            if (self.__taxaJuros + x) <= 0:
                raise ValueError(f"Erro: Valor {valor} inválido.")
        except TypeError as e:
            raise TypeError from e
        self.__taxaJuros += x
    
    

if __name__ == '__main__':
    c1 = contaInvestimento(1, 0.10, 'sla', 1000)
    for i in range(5)
        c1.adicioneJuros(0.10)
    print(c1)