MESES: tuple = (
    "Janeiro",
    "Fevereiro",
    "Março",
    "Abril",
    "Maio",
    "Junho",
    "Julho",
    "Agosto",
    "Setembro",
    "Outubro",
    "Novembro",
    "Dezembro",
)


def gerar_lista() -> list[float]:
    temps: list = []
    i: int = 0
    try:
        while i < 12:
            temps.append(float(input("Digite o valor da temperatura(em °C):")))
            i += 1
        return temps
    except TypeError as error:
        print("Erro. Digite apenas números.")
        return gerar_lista()


def gerar_media(valores: list) -> float:
    out: float = 0.0
    try:
        for valor in valores:
            out += float(valor)
        return out
    except TypeError:
        print("Erro. Digite apenas números.")
        return gerar_media()


if __name__ == "__main__":
    temps = gerar_lista()
    media = gerar_media(temps)
    for i in range(len(MESES)):
        print(f"{i} - {MESES[i]}: {temps[i]}°C")
    print("A média dos valores foi: {media}°C")
