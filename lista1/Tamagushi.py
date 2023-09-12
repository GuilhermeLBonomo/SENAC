#! /home/gui/pyenvs/main/bin/python


class Tamagushi:
    def __init__(self, nome: str, saude: str, idade: int, fome: bool) -> None:
        self.__nome = nome.capitalize()
        self.__saude = saude
        self.__idade = idade
        self.__fome = fome

    @property
    def nome(self) -> str:
        return self.__nome

    @nome.setter
    def nome(self, novo_nome: str) -> None:
        self.__nome = novo_nome.capitalize()

    @property
    def saude(self) -> str:
        return self.__saude

    @saude.setter
    def saude(self, nova_saude: str) -> None:
        self.__saude = nova_saude

    @property
    def idade(self) -> int:
        return self.__idade

    @idade.setter
    def idade(self, nova_idade: int) -> None:
        try:
            x = int(nova_idade)
        except TypeError as e:
            raise TypeError from e
        if nova_idade < 0:
            raise ValueError("A idade não pode ser menor que 0.")
        self.__idade = x

    @property
    def fome(self) -> bool:
        return self.__satisfeito

    @fome.setter
    def fome(self, esta_satisfeito: bool) -> None:
        self.__satisfeito = esta_satisfeito

    @property
    def humor(self) -> str:
        if self.__saude == "Saudável" and self.__satisfeito:
            return "Feliz"
        elif self.__saude == "Saudável":
            return "Contente"
        else:
            return "Triste"

    def __str__(self) -> str:
        return f"Nome: {self.__nome}\nSaúde: {self.__saude}\nIdade: {self.__idade} anos\nSatisfeito: {self.__fome}"


if __name__ == "__main__":
    t1 = Tamagushi("uga", "Saudável", 3000, False)
    print(t1)
