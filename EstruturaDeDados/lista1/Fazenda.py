from Tamagushi2 import Tamagushi2
from random import randint


class Fazenda:
    def __init__(self) -> None:
        self.tamagushi_list = []

    def adicionar_tamagushi(self, tamagushi: Tamagushi2) -> None:
        if not isinstance(tamagushi, Tamagushi2):
            raise TypeError
        self.tamagushi_list.append(tamagushi)

    def alimentar_todos(self, quantidade_comida: float) -> None:
        try:
            x = float(quantidade_comida)
            if x <= 0:
                raise ValueError(f"Erro: Valor {quantidade_comida} inválido.")
        except TypeError as e:
            raise TypeError from e

        for tamagushi in self.tamagushi_list:
            tamagushi.comida_fornecida = quantidade_comida

    def brincar_com_todos(self, tempo_brincadeira: float) -> None:
        try:
            x = float(tempo_brincadeira)
            if x <= 0:
                raise ValueError(f"Erro: Valor {tempo_brincadeira} inválido.")
        except TypeError as e:
            raise TypeError from e

        for tamagushi in self.tamagushi_list:
            tamagushi.tempo_brincado = tempo_brincadeira


if __name__ == "__main__":
    fazenda = Fazenda()
    num_tamagushi = randint(5, 10)
    for _ in range(num_tamagushi):
        nome = f"Tamagushi_{randint(1, 100)}"
        saude = randint(0, 100)
        idade = randint(0, 10)
        fome = randint(0, 100)
        tamagushi = Tamagushi2(nome, saude, idade, fome)
        fazenda.adicionar_tamagushi(tamagushi)
    fazenda.alimentar_todos(30)
    fazenda.brincar_com_todos(2)
    for i, tamagushi in enumerate(fazenda.tamagushi_list):
        print(f"Tamagushi {i + 1}:")
        print(tamagushi)
        print("-" * 20)
