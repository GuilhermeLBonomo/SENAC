from Tamagushi import Tamagushi


class Tamagushi2(Tamagushi):
    def __init__(self, nome: str, saude: int, idade: int, fome: int) -> None:
        super().__init__(nome, saude, idade, fome)
        self.__comida_fornecida = 0
        self.__tempo_brincado = 0

    @property
    def comida_fornecida(self) -> int:
        return self.__comida_fornecida

    @comida_fornecida.setter
    def comida_fornecida(self, quantidade: int) -> None:
        self.__comida_fornecida = self.validar_valor(quantidade)

    @property
    def tempo_brincado(self) -> int:
        return self.__tempo_brincado

    @tempo_brincado.setter
    def tempo_brincado(self, tempo: int) -> None:
        self.__tempo_brincado = self.validar_valor(tempo)

    def passar_tempo(self, horas: int) -> None:
        super().passar_tempo(horas)
        self.fome -= self.__comida_fornecida * horas
        self.tedio -= self.__tempo_brincado * horas

    def __str__(self) -> str:
        return (
            f"Nome: {self.nome}\nSaúde: {self.saude}\nIdade: {self.idade} anos\nFome: {self.fome}\n"
            f"Comida fornecida: {self.comida_fornecida}\nTempo brincado: {self.tempo_brincado} horas\n"
            f"Humor: {self.humor()}"
        )


if __name__ == "__main__":
    t2 = Tamagushi2("aaa", 22, 989, 90)
    print(t2)
