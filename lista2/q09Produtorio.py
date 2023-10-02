def Produtorio(num: int) -> int:
    """Função para calcular o produtório de um número"""
    if num == 0:
        return 1
    else:
        return num * Produtorio(num - 1)


def Produtorio_valido(num: int) -> int:
    """Função para calcular o produtório de um número"""
    try:
        SINAL = -1 if (num < 0 and num % 2 != 0) else 1
        num = abs(int(num))
    except TypeError as e:
        raise TypeError from e
    if num < 0:
        raise ValueError
    return Produtorio(num) * SINAL


def pegar_dado() -> int:
    """Função para pegar um dado válido"""
    try:
        num = int(input('Digite um número: '))
        return num
    except ValueError:
        print('Digite apenas números inteiros')
        return pegar_dado()


if __name__ == '__main__':
    num = pegar_dado()
    print(f'O produtório de {num} é {Produtorio_valido(num)}')
