#! /home/gui/pyenvs/main/bin/python


class Conta_Corrente:
    def __init__(self, numero: int, nome: str, saldo=0.0) -> None:
        self.__numero = 0
        self.__nome = nome
        self.__saldo = saldo

    @property
    def numero(self) -> int:
        return self.__numero

    @property
    def nome(self) -> str:
        return self.__nome

    @property
    def saldo(self) -> float:
        return self.__saldo

    def alterarNome(self, novo_nome: str) -> None:
        self.__nome = str(novo_nome).capitalize()

    def deposito(self, valor: float) -> None:
        try:
            x = float(valor)
            if x <= 0:
                raise ValueError(f"Erro: Valor {valor} inválido.")
            self.__saldo += valor
        except TypeError as e:
            raise ValueError(f"Erro: Valor {valor} inválido.") from e

    def saque(self, valor: float) -> float:
        try:
            x = float(valor)
            valor_saque = 0
            if x <= 0:
                raise ValueError(f"Erro: Valor {valor} inválido.")
            if valor > self.__saldo:
                confirmacao = input(
                    f"Seu saldo é insuficente em {valor - self.__saldo:.2f} R$.\n Deseja sacar todo o seu saldo mesmo assim? [S/N]"
                ).lower()
                if confirmacao != "s":
                    return 0
                valor_saque = self.__saldo
                self.__saldo = 0
            else:
                valor_saque = valor
                self.__saldo -= valor
            return valor_saque
        except TypeError as e:
            raise ValueError(f"Erro: Valor {valor} inválido.") from e

    def __str__(self) -> str:
        return (
            f"Nome:{self.__nome}\n"
            f"Número:{self.__numero}\n"
            f"Saldo:{(self.__saldo):.2f}\n"
        )


if __name__ == "__main__":
    c1 = Conta_Corrente(3, "Mário", 100)
    print(c1)
    c1.deposito(25.99)
    print(c1)
    s = c1.saque(130)
    print(f"Valor sacado: {s} R$")
    print(c1)
