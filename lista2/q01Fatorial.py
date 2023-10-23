from functools import lru_cache


class Fatorial:
    def __init__(self, num: int) -> None:
        try:
            x = int(num)
            if x < 0:
                raise ValueError
        except TypeError as error:
            raise TypeError from error
        self.__numero_fatorial = Fatorial.fatorial(x)
        self.__numero = x

    def mudar_numero(self, novo_numero: int) -> None:
        try:
            x = int(num)
            if x < 0:
                raise ValueError
        except TypeError as error:
            raise TypeError from error
        self.__numero_fatorial = Fatorial.fatorial(novo_numero)
        self.__numero = x

    def __str__(self) -> str:
        return f"O fatorial de {self.__numero} é: {self.__numero_fatorial}"

    @staticmethod
    def fatorial(numero: int) -> int:
        try:
            x = int(numero)
            if x < 0:
                raise ValueError
        except TypeError as error:
            raise TypeError from error

        return Fatorial.__calc_fatorial(x)

    @staticmethod
    @lru_cache
    def __calc_fatorial(numero: int) -> int:
        try:
            x = int(numero)
            if x < 0:
                raise ValueError
        except TypeError as error:
            raise TypeError from error

        if numero in (0, 1):
            return numero
        return numero * Fatorial.fatorial(numero - 1)


if __name__ == "__main__":
    num = abs(int(input("Digite um número inteiro positivo: ")))
    fat = Fatorial(num)
    print(fat)
