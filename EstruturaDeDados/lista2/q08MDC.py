def MDC(x, y):
    """Função para calcular o MDC de dois números inteiros"""
    if y == 0:
        return x
    else:
        return MDC(y, x % y)


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
    print(f'O MDC de {x} e {y} é {MDC(x, y)}')
