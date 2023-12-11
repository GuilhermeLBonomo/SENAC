class Funcionario:
    def __init__(self, nome: str, salario: float) -> None:
        self.__nome = nome
        self.__salario = 0.0
        self.salario = salario

    @property
    def nome(self) -> str:
        return self.__nome

    @property
    def salario(self) -> float:
        return self.__salario

    @salario.setter
    def salario(self, novo_salario: float) -> None:
        if novo_salario < 0:
            raise ValueError("Salário não pode ser menor que 0")
        self.__salario = novo_salario

    def __str__(self) -> str:
        return f"Nome: {self.nome}\n" f"Salário: R${self.salario:.2f}"


if __name__ == "__main__":
    f1 = Funcionario("Francisco", 2567.98)
    print(f1)
