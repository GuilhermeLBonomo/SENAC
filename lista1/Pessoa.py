#! /home/gui/pyenvs/main/bin/python


class Pessoa:
    def __init__(self, nome: str, idade: int, peso: float, altura: float) -> None:
        self.__nome = nome
        self.__idade = 0  # anos
        self.__peso = 0.0  # kg
        self.__altura = 0.0  # cm
        self.envelhecer(idade)
        self.__altura = 0.0
        self.crescer(altura)
        self.engordar(peso)

    def crescer(self, valor: float) -> None:
        try:
            x = float(valor)
            if x < 0:
                raise ValueError(f"Erro: O valor {valor} é inválido.")
            self.__altura += x
        except TypeError as e:
            raise ValueError(f"Erro: O valor {valor} é inválido.")

    def envelhecer(self, valor=1) -> None:
        try:
            x = int(valor)
            if x < 0:
                raise ValueError(f"Erro: O valor {valor} é inválido.")
            idade_antiga = self.__idade
            self.__idade += x

            if idade_antiga < 21:
                envelhecimento_ate_21 = (
                    (21 - idade_antiga) if (self.__idade >= 21) else x
                )
                self.crescer(envelhecimento_ate_21 * 0.5)

        except TypeError:
            raise ValueError(f"Erro: O valor {valor} é inválido.")

    def engordar(self, valor, reverse=False) -> None:
        num = -1 if reverse else 1
        try:
            x = float(valor) * num
            if x >= self.__peso and num == -1:
                raise ValueError(f"Erro: O valor {valor} é inválido.")
            self.__peso += x
        except TypeError as e:
            raise ValueError(f"Erro: O valor {valor} é inválido.") from e

    def emagrecer(self, valor) -> None:
        self.engordar(valor, reverse=True)

    @property
    def nome(self) -> str:
        return self.__nome

    @nome.setter
    def nome(self, novo_nome: str) -> None:
        self.__nome = novo_nome

    @property
    def idade(self) -> int:
        return self.__idade

    @property
    def peso(self) -> float:
        return self.__peso

    @property
    def altura(self) -> float:
        return self.__altura

    def __str__(self) -> str:
        return (
            f"Nome: {self.__nome}\n"
            f"Idade: {self.__idade}\n"
            f"Peso: {self.__peso:.3f} Kg\n"
            f"Altura: {self.__altura:.3f} cm\n"
        )


if __name__ == "__main__":
    # print(dir(Pessoa))
    p1 = Pessoa("João", 19, 83.00, 190)
    print(p1)
    p1.envelhecer(1)
    print(p1)
    p1.emagrecer(10)
    print(p1)
