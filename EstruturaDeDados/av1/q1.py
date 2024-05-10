from enum import Enum


class MESES(Enum):
    Janeiro = 0
    Fevereiro = 1
    Março = 2
    Abril = 3
    Maio = 4
    Junho = 5
    Julho = 6
    Agosto = 7
    Setembro = 8
    Outubro = 9
    Novembro = 10
    Dezembro = 11


def gerar_lista() -> list[float]:
    temps: list[float] = [0.0]*12
    i: int = 0
    try:
        while i < 12:
            temps[i] = float(input("Digite o valor da temperatura(em °C):"))
            i += 1
        return temps
    except TypeError as error:
        print(f"Erro: {error}.\nDigite apenas números.")
        return gerar_lista()


def gerar_media(valores: list) -> float:
    soma: float = sum(valores)
    media: float = soma / len(valores)
    return media


if __name__ == "__main__":
    print(MESES.name())
    temps = gerar_lista()
    media = gerar_media(temps)
    for mes in MESES:
        print(f"{mes.value} - {mes.name}: {temps[mes.value]}°C")
    print(f"A média dos valores foi: {media:.2f}°C")
