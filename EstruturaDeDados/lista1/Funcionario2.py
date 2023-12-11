from Funcionario import Funcionario


class Funcionario2(Funcionario):
    def __init__(self, nome: str, salario: float) -> None:
        super().__init__(nome, salario)

    def aumentar_salario(self, percentual: float) -> None:
        try:
            x = float(percentual) / 100
            if x <= 0:
                raise ValueError(f"Erro: Valor {percentual} inválido.")
        except TypeError as e:
            raise TypeError from e
        self.salario += self.salario * x


if __name__ == "__main__":
    f1 = Funcionario2("Irmão do Francisco", 2567.98)
    f1.aumentar_salario(10)
    print(f1)
