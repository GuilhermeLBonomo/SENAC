def pegar_dados() -> int:
    try:
        return abs(int(input('Digite um número inteiro positivo: ')))
    except ValueError:
        print('Digite apenas números inteiros positivos')
        return pegar_dados()


def contagem(n: int, rev=False) -> None:
    """Função para contar de 0 até n"""
    if n == 0:
        print(n)
    else:
        if rev:
            print(n)
            contagem_rev(n - 1)
        else:
            contagem(n - 1)
            print(n) 


def contagem_rev(n: int) -> None:
    """Função para contar de n até 0"""
    contagem(n, True)


def contagem_par_crescente(n: int, rev=False) -> None:
    """Função para contar de 0 até n de 2 em 2"""
    if n == 0:
        print(n)
    else:
        if rev:
            print(n)
            contagem_par_crescente(n - 2, True)
        else:
            contagem_par_crescente(n - 2)
            print(n)


def contagem_par_decrescente(n: int) -> None:
    """Função para contar de n até 0 de 2 em 2"""
    contagem_par_crescente(n, True)


if __name__ == '__main__':
    n = pegar_dados()
    contagem(n)
    print('\n\n')
    contagem_rev(n)
    print('\n\n')
    contagem_par_crescente(n)
    print('\n\n')
    contagem_par_decrescente(n)
    print('\n\n')
