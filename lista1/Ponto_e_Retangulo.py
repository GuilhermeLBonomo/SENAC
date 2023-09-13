from math import pow, sqrt


class Ponto_2D:
    def __init__(self, x: float, y: float) -> None:
        self.__values = [0.0, 0.0]
        self.values(x)
        self.values(y, False)

    def values(self, x=True) -> float:
        n = 0 if x else 1
        return self.__values[n]

    def values(self, new_value, x=True) -> None:
        n = 0 if x else 1
        try:
            new_value = float(new_value)

        except ValueError as e:
            raise ValueError from e
        self.__values[n] = new_value

    def __str__(self) -> str:
        return f"Ponto 2D com valores: ({self.__values[0]};{self.__values[1]})"


class Retangulo:
    def __init__(
        self, largura: float, altura: float, ponto_inicial=Ponto_2D(0.0, 0.0)
    ) -> None:
        self.__valores = [0.0, 0.0]
        self.valores(largura)
        self.valores(altura, False)
        self.__ponto_inicial = ponto_inicial

    def valores(self, largura=True) -> float:
        n = 0 if largura else 1
        return self.__valores[n]

    def valores(self, new_value, largura=True) -> None:
        n = 0 if largura else 1
        try:
            new_value = float(new_value)
            if new_value <= 0:
                raise ValueError

        except ValueError as e:
            raise ValueError from e
        self.__valores[n] = new_value

    def calcular_ponto_central(self) -> Ponto_2D:
        pt = self.__ponto_inicial._Ponto_2D__values
        # print(dir(pt))
        x_central = (self.__valores[0] + pt[0]) / 2
        y_central = (self.__valores[1] + pt[1]) / 2
        new_point = Ponto_2D(x_central, y_central)
        return new_point

    def __str__(self) -> str:
        return (
            f"Largura: {self.__valores[0]}\n"
            f"Altura: {self.__valores[1]}\n"
            f"Ponto inicial: {self.__ponto_inicial}\n"
            f"Ponto médio: {self.calcular_ponto_central()}"
        )


if __name__ == "__main__":
    p_i = Ponto_2D(0.0, 0.0)
    r1 = Retangulo(3, 4, p_i)
    print(r1)
