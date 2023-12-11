def pegar_dado() -> int:
    """Função para pegar um dado válido"""
    try:
        x = abs(int(input('Digite um número inteiro ímpar: ')))
        if x % 2 == 0:
            raise ValueError('O número deve ser ímpar')
        return x
    except ValueError as e:
        print('Digite apenas números inteiros ímpares')
        return pegar_dado()


def fatorial_duplo(n: int) -> int:
    """Função para calcular o fatorial duplo de um número"""
    if n == 1:
        return 1
    else:
        return n * fatorial_duplo(n - 2)


if __name__ == '__main__':
    n = pegar_dado()
    print(f'O fatorial duplo de {n} é {fatorial_duplo(n)}')
