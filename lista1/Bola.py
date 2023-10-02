#! /home/gui/pyenvs/main/bin/python


class Bola:
    """Classe que representa uma bola tendo cor, circunferência e material como atributos."""

    def __init__(self, color, circunference: float, material: str) -> None:
        self.__color = color.capitalize()
        self.__circunference = circunference
        self.__material = material.capitalize()

    def __str__(self) -> str:
        return f"Color: {self.__color}\nCircunference: {self.__circunference}\nMaterial: {self.__material}"

    def trocaCor(self, new_color) -> None:
        if isinstance(new_color, str):
            self.__color == new_color.capitalize()
        elif (isinstance(new_color, list) or isinstance(new_color, tuple)) and len(
            new_color
        ) == 3:
            for num in new_color:
                num = int(num)
                if 0 <= num <= 255:
                    self.__color = new_color
        else:
            raise ValueError

    def mostaCor(self) -> None:
        print(self.__color)

    @property
    def circunference(self) -> float:
        return self.__circunference

    @circunference.setter
    def circunference(self, value) -> None:
        if isinstance(value, str):
            value = value.replace(",", ".")
        try:
            self.__circunference = float(value)
        except TypeError:
            raise ValueError

    @property
    def material(self) -> str:
        return self.__material

    @material.setter
    def material(self, m) -> None:
        self.__material = m


if __name__ == "__main__":
    b = Bola("Black", 2.781, "ferro")
    print(b)
    b.circunference = 30
    print(b)
