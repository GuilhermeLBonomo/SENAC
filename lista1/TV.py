#! /home/gui/pyenvs/main/bin/python


class TV:
    def __init__(self, numero_canal=0, volume=50) -> None:
        self.__numero_total_de_canais = 350  # Considerando que hajam 350 canais...
        self.__volume_max = 100
        self.__numero_canal = 0
        self.__volume = 50
        self.canal = numero_canal
        self.volume = volume

    @property
    def canal(self) -> int:
        return self.__numero_canal

    @canal.setter
    def canal(self, valor: int) -> None:
        try:
            novo_valor = int(valor)
        except TypeError as e:
            raise ValueError from e
        if 0 <= novo_valor <= self.__numero_total_de_canais:
            self.__numero_canal = novo_valor
        else:
            raise ValueError

    @property
    def volume(self) -> int:
        return self.__volume

    @volume.setter
    def volume(self, valor: int) -> None:
        try:
            novo_valor = int(valor)
        except TypeError as e:
            raise ValueError from e
        if 0 <= novo_valor <= self.__volume_max:
            self.__volume = novo_valor
        else:
            raise ValueError

    def variar_canal(self, variacao: int) -> None:
        try:
            novo_valor = int(self.__numero_canal + variacao)
        except TypeError as e:
            raise ValueError from e
        if 0 <= novo_valor <= self.__numero_total_de_canais:
            self.__numero_canal = novo_valor
        else:
            raise ValueError

    def variar_volume(self, variacao: int) -> None:
        try:
            novo_valor = int(self.__volume + variacao)
        except TypeError as e:
            raise ValueError from e
        if 0 <= novo_valor <= self.__volume_max:
            self.__volume = novo_valor
        else:
            raise ValueError

    def __str__(self) -> str:
        return f"Canal: {self.__numero_canal}\n" f"Volume: {self.__volume}\n"


if __name__ == "__main__":
    t1 = TV()
    print(t1)
