def Multip_Rec(n1: int, n2: int) -> int:
    """Função para calcular a multiplicação de dois números inteiros"""
    SINAL = 1 if ((n1 > 0 and n2 > 0) or (n1 < 0 and n2 < 0)) else -1
    n1 = abs(n1)
    n2 = abs(n2)
    if n1 == 0 or n2 == 0:
        return 0
    else:
        return n1*SINAL + Multip_Rec(n1*SINAL, n2 - 1)


def pegar_dados() -> tuple[int, int]:
    """Função para pegar os dados válidos"""
    try:
        x = int(input('Digite o valor inteiro de x: '))
        y = int(input('Digite o valor inteiro de y: '))
        return x, y
    except ValueError:
        print('Digite apenas números inteiros')
        return pegar_dados()


if __name__ == '__main__':
    x, y = pegar_dados()
    print(f'{x} vezes {y} é igual a {Multip_Rec(x, y)}')
