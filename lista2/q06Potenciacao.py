def potencia(k: int, n: int) -> int:
    """Função para calcular a potência de um número"""
    if n == 0:
        return 1
    else:
        return k * potencia(k, n - 1)


def pegar_dados() -> tuple[int, int]:
    """Função para pegar os dados válidos"""
    try:
        k = int(input('Digite o valor inteiro de k: '))
        n = abs(int(input('Digite o valor inteiro positivo de n: ')))
        return k, n
    except ValueError:
        print('Digite apenas números inteiros')
        return pegar_dados()


if __name__ == '__main__':
    k, n = pegar_dados()
    print(f'{k} elevado a {n} é igual a {potencia(k, n)}')
