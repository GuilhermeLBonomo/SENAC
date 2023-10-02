#! /home/gui/pyenvs/main/bin/python


class Retangulo:
    def __init__(self, ladoA: float, ladoB: float) -> None:
        self.__lados = [0.0, 0.0]  # 0 = Base; 1 = Altura
        self.mudar_lado(ladoA)
        self.mudar_lado(ladoB, False)
        self.__area = self.calcular_area()
        self.__perimetro = self.calcular_perimetro()

    @property
    def lados(self) -> list[float, float]:
        return self.__lados

    @property
    def area(self) -> float:
        return self.__area

    @property
    def perimetro(self) -> float:
        return self.__perimetro

    def mudar_lado(self, novo_lado: float, mudar_base=True) -> None:
        base_ou_altura = 0 if mudar_base else 1
        try:
            lado_novo = float(novo_lado)
        except TypeError as e:
            raise TypeError(
                f'Erro: o valor "{lado}" precisa ser um número real positivo, maior que 0.'
            ) from e
        if lado_novo <= 0:
            raise ValueError(
                f'Erro: o valor "{lado}" precisa ser um número real positivo, maior que 0.'
            )
        self.__lados[base_ou_altura] = lado_novo

    def calcular_area(self) -> float:
        return self.__lados[0] * self.__lados[1]

    def calcular_perimetro(self) -> float:
        return 2 * self.__lados[0] + 2 * self.__lados[1]

    def __str__(self) -> str:
        return f"Retângulo de Lados: {self.__lados[0]:.3f} & {self.__lados[1]:.3f}\nÁrea: {self.area:.3f}\nPerímetro:{self.perimetro:.3f}"


if __name__ == "__main__":
    x = input("Infome a medida da largura (sem as unidades)")
    y = input("Infome a medida do comprimento (sem as unidades)")
    unidade = input("Informe as unidades usadas: \n[m, cm]")
    print("\n\n")
    r1 = Retangulo(x, y)
    print(
        f"Considerando pisos de 1{unidade}:1{unidade}, serão necessários: {r1.area:.3f}{unidade}² de piso."
    )
    print(f"Também será necessário um rodapé de {r1.perimetro}{unidade}.")
