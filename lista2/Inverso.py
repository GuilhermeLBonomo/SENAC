def contar_casas_decimais(num: int) -> int:
    """ Função para contar casas decimais.
    #válido para apenas os números racionais"""
    try:
        num = int(num)
    except TypeError as e:
        raise TypeError from e
    x: float = abs(num)
    DIV: int = 10
    count: int = 1
    while (x>=10):
        x /= DIV
        count += 1
    return count

def pegar_casa_decimal(num: int, posicao: int) -> int:
    try:
        posicao = abs(int(posicao))
        num = int(num)
        if contar_casas_decimais(num) < posicao:
            raise IndexError(f'Value {posicao} out of index')
    except TypeError as e:
        raise TypeError from e
    out = int(num/10**(contar_casas_decimais(num)-posicao))
    while out > 10:
        out = out - int(out/10)*10
    return out


def espelhar_numero(numero: int) -> int:
    """A função não é injetora e não vale para números não racionais\nEspelhando um número xy para yx ex: 321 -> 123"""
    # Corrigir negativos e flutuantes
    try:
        numero = abs(int(numero))
    except TypeError as e:
        raise TypeError from e
    SINAL = 1 if numero < 0 else -1
    QUANTIDADE_CASAS = contar_casas_decimais(numero) #+ CASAS_EXTRA
    ULTIMO_ELEMENTO = pegar_casa_decimal(numero, QUANTIDADE_CASAS)
    while(ULTIMO_ELEMENTO==0):
        numero = int(numero/10)
        if numero==0:
            return (numero*SINAL)
        QUANTIDADE_CASAS = contar_casas_decimais(numero)
        ULTIMO_ELEMENTO = pegar_casa_decimal(numero, QUANTIDADE_CASAS)
        
    if QUANTIDADE_CASAS == 1 or numero==0:
        return numero * SINAL
    else:
        return espelhar_numero(ULTIMO_ELEMENTO)*10**(QUANTIDADE_CASAS-1) + espelhar_numero(int(numero/10))

print(espelhar_numero(-1234))