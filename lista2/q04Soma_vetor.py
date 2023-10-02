def soma_vetor_valido(vetor: list[int]) -> int:
    """Função para validar e somar os elementos de um vetor recursivamente"""
    try:
        if not isinstance(vetor, list):
            raise TypeError('O vetor deve ser uma lista')
        for value in vetor:
            if not isinstance(value, int):
                raise TypeError('O vetor deve ser uma lista de inteiros')
    except TypeError as error:
        raise error
    return soma_vetor(vetor)


def soma_vetor(vetor) -> int:
    """Função para somar os elementos de um vetor recursivamente"""
        if len(vetor) == 1:
            return vetor[0]
        else:
            return vetor[0] + soma_vetor(vetor[1:])


if __name__ == '__main__':
    lista = [x for x in range(1, 11)]
    print(soma_vetor_valido(lista))
