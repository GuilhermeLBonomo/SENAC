from Conta_Corrente import Conta_Corrente

<<<<<<< HEAD
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
=======

class contaInvestimento(Conta_Corrente):
    def __init__(self, numero: int, taxaJuros: float, nome: str, saldo=0.0) -> None:
        super().__init__(numero, nome, saldo)
        self.__taxaJuros = taxaJuros/100

    def adicioneJuros(self) -> None:
        juros = self.saldo * self.__taxaJuros
        self.deposito(juros)

    def __str__(self) -> str:
        return (
            f"Nome: {self.nome}\n"
            f"Número: {self.numero}\n"
            f"Saldo: R${self.saldo:.2f}\n"
            f"Taxa de Juros: {self.__taxaJuros * 100:.2f}%\n"
        )


if __name__ == "__main__":
    c1 = contaInvestimento(1, 10, "sla", 1000)
    for i in range(5):
        c1.adicioneJuros()
    print(c1)
>>>>>>> origin/main
