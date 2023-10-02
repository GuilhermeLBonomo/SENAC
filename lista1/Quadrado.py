#! /home/gui/pyenvs/main/bin/python
from math import pow


class Quadrado:
    """Classe que representa um  quadrado tendo um tamanho do lado e a sua área como atributos."""

    def __init__(self, lado_inicial: float) -> None:
        self.__lado = lado_inicial
        self.__area = self.calcular_area()

    def calcular_area(self) -> float:
        area = pow(self.__lado, 2)
        return area

    @property
    def lado(self) -> float:
        return self.__lado

    @lado.setter
    def lado(self, lado: float) -> None:
        new_lado = 0.0
        try:
            new_lado = float(lado)
        except TypeError as e:
            raise TypeError(
                f'Erro: o valor "{lado}" precisa ser um número real positivo, maior que 0.'
            ) from e
        if new_lado <= 0:
            raise ValueError(
                f'Erro: o valor "{lado}" precisa ser um número real positivo, maior que 0.'
            )
        self.__lado = new_lado

    def __str__(self) -> str:
        return f"Quadrado de Lado: {self.__lado:.3f} e Área: {self.__area:.3f}"


if __name__ == "__main__":
    q1 = Quadrado(5)
    print(q1)
    q1.lado = 9.1234
    print(q1)
