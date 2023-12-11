def vetor_reverso(vetor: list) -> list:
    """Inverte a ordem dos elementos de um vetor."""
    if vetor == []:
        return []
    else:    
        return [vetor[-1]] + vetor_reverso(vetor[:-1])


if __name__ == '__main__':
    vetor = [x for x in range(0, 100)]
    print(f'Vetor: {vetor}')
    print(f'Vetor Reverso: {vetor_reverso(vetor)}')
