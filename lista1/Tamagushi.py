#! /home/gui/pyenvs/main/bin/python


class Tamagushi:
<<<<<<< HEAD
    def __init__(self, nome: str, saude: str, idade: int, fome: bool) -> None:
        self.__nome = nome.capitalize()
        self.__saude = saude
        self.__idade = idade
        self.__fome = fome
=======
    def __init__(self, nome: str, saude: int, idade: int, fome: int) -> None:
        self.__nome = nome.capitalize()
        self.__saude = self.validar_valor(saude)
        self.__idade = idade
        self.__fome = self.validar_valor(fome)

    def validar_valor(self, valor: int) -> int:
        return min(max(valor, 0), 100)
>>>>>>> origin/main

    @property
    def nome(self) -> str:
        return self.__nome

    @nome.setter
    def nome(self, novo_nome: str) -> None:
        self.__nome = novo_nome.capitalize()

    @property
<<<<<<< HEAD
    def saude(self) -> str:
        return self.__saude

    @saude.setter
    def saude(self, nova_saude: str) -> None:
        self.__saude = nova_saude

    @property
=======
>>>>>>> origin/main
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
<<<<<<< HEAD
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
=======
    def fome(self) -> int:
        return self.__fome

    @fome.setter
    def fome(self, valor: int) -> None:
        self.__fome = self.validar_valor(valor)

    @property
    def saude(self) -> int:
        return self.__saude

    @saude.setter
    def saude(self, valor: int) -> None:
        self.__saude = self.validar_valor(valor)

    def humor(self) -> str:
        if self.__saude >= 50 and self.__fome <= 50:
            return "Feliz"
        elif self.__saude >= 50:
>>>>>>> origin/main
            return "Contente"
        else:
            return "Triste"

    def __str__(self) -> str:
<<<<<<< HEAD
        return f"Nome: {self.__nome}\nSaúde: {self.__saude}\nIdade: {self.__idade} anos\nSatisfeito: {self.__fome}"


if __name__ == "__main__":
    t1 = Tamagushi("uga", "Saudável", 3000, False)
=======
        return (
            f"Nome: {self.__nome}\n"
            f"Saúde: {self.__saude}\n"
            f"Idade: {self.__idade} anos\n"
            f"Fome: {self.__fome}\n"
            f"Humor: {self.humor()}"
        )


if __name__ == "__main__":
    t1 = Tamagushi("uga", 90, 3000, 33)
>>>>>>> origin/main
    print(t1)
