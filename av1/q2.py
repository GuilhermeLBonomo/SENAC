def calculo(n: float) -> float:
    SINAL: int = -1 if n < 0 else 1
    n = abs(n)
    if n == 1:
        return 1 * SINAL
    else:
        return (1 / n * SINAL) + calculo((n - 1) * SINAL)


def gerar_calculo() -> float:
    try:
        x = float(input("Digite um número inteiro: "))
        if x == 0:
            return gerar_calculo()
        else:
            return calculo(x)
    except TypeError:
        return gerar_calculo()


if __name__ == "__main__":
    print(gerar_calculo())
