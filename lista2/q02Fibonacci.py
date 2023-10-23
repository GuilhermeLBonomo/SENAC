from functools import lru_cache


class Fibonacci:
    def __init__(self, n_valor: int) -> None:
        try:
            x_value = int(n_valor)
            if x_value < 0:
                raise ValueError
        except TypeError as error:
            raise TypeError from error
        self.__n_valor = x_value
        self.__n_fib = Fibonacci.fib(n_valor)

    @staticmethod
    def fib(num: int) -> int:
        try:
            x_value = int(num)
            if x_value < 0:
                raise ValueError
        except TypeError as error:
            raise TypeError from error
        return Fibonacci.__calc_fib(x_value)

    @staticmethod
    @lru_cache
    def __calc_fib(num: int) -> int:
        if num in (0, 1):
            return num
        return Fibonacci.fib(num - 1) + Fibonacci.fib(num - 2)

    def __str__(self) -> str:
        return f"O {self.__n_valor+1}° termo da sequência Fibonacci é: {self.__n_fib}"


if __name__ == "__main__":
    num = abs(int(input("Digite um número inteiro positivo (Vale 0): ")))
    fi = Fibonacci(num)
    print(fi)
