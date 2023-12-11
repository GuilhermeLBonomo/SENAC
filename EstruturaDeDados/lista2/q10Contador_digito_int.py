from q03Inverso import pegar_casa_decimal, contar_casas_decimais


def pegar_dados() -> tuple:
    """Função para pegar os dados"""
    try:
        valor = int(input('Digite um valor inteiro positivo: '))
        num = abs(int(input('Digite um número inteiro: ')))
        return valor, num
    except ValueError:
        print('Digite apenas números inteiros')
        return pegar_dados()


def contar_int_em_int(valor: int, num: int) -> int:
    """Função para contar quantas vezes um inteiro aparece em outro inteiro"""
    if num < valor:
        return 0
    elif num == valor:
        return 1
    else:
        x = valor
        y = 0
        for i in range(1, contar_casas_decimais(valor) + 1):
            y += pegar_casa_decimal(num, i) * 10**(contar_casas_decimais(valor) - i)
        novo_numero = num - (pegar_casa_decimal(num, 1) * 10**(contar_casas_decimais(num)-1))
        if x == y:
            return 1 + contar_int_em_int(valor, novo_numero)
        else:
            return contar_int_em_int(valor, novo_numero)


if __name__ == '__main__':
    # 2 em 762021192 = 3
    x, y = pegar_dados()
    print(contar_int_em_int(x, y))
