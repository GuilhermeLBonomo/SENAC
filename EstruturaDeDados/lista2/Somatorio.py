from time import time

class Contagem:

    @staticmethod
    def contagem(n: int) -> int:
        try:
            n = int(n)
        except TypeError as e:
            raise TypeError from e
        return Contagem.__contagem(n)
    
    
    @staticmethod
    def __contagem(n: int) -> int:
        SINAL = -1 if n <= 0 else 1
        n = abs(n)
        if n == 1 or n == 0:
            return (n * SINAL)
        else:
            return n + Contagem.contagem(n-1)

    @staticmethod
    def contagem_for_comparacao(n: int) -> int:
        try:
            n = int(n)
        except TypeError as e:
            raise TypeError from e
        return Contagem.__contagem_for_comparacao(n)


    @staticmethod
    def __contagem_for_comparacao(n: int) -> int:
        SINAL = -1 if n <= 0 else 1
        tot = 0
        for num in range(x+1):
            tot += num
        return tot * SINAL
    

if __name__ == '__main__':
    x = 450
    t1 = time()
    y = Contagem.contagem(x)
    t2 = time()
    z = Contagem.contagem_for_comparacao(x)
    t3 = time()
    
    print(f'Recursivo:  Valor: {y} - Tempo: {t2-t1}s')
    print(f'For:  Valor: {z} - Tempo: {t3-t2}s')