from datetime import datetime
import random


# Guilherme Lopes, Bernardo Gondim, Rafael Antunes, Ilan Pires
class QuickSort:
    @staticmethod
    def QuickSort(arr: list, pivot=0) -> list:
        try:
            pivot: int = int(pivot)
        except ValueError as e:
            raise ValueError(f"Erro: O valor {pivot} é inválido") from e
        if pivot >= len(arr):
            raise ValueError(f"Erro: O valor {pivot} é maior que o tamanho do array")
        else:
            return QuickSort.__QuickSort(arr, pivot)

    @staticmethod
    def __QuickSort(arr: list, pivot=0) -> list:
        if len(arr) <= 1:
            return arr
        middle_index: int = len(arr) // 2
        pivot_value = arr[middle_index] if pivot != -1 else arr[0]
        menores: list = [x for x in arr if x < pivot_value]
        iguais: list = [x for x in arr if x == pivot_value]
        maiores: list = [x for x in arr if x > pivot_value]
        return QuickSort.__QuickSort(menores) + iguais + QuickSort.__QuickSort(maiores)


def measure_time(sort_method, arr: list) -> float:
    start_time: datetime = datetime.now()
    sort_method(arr)
    end_time: datetime = datetime.now()
    return (end_time - start_time).total_seconds()


if __name__ == "__main__":
    lista_strings: list[str] = ["", "🥚", "🐔", "ab", "aab", "cd", "z"]
    lista_exemplo1: list[int] = [4, 5, 6, 7, 3, 1]
    LENGTH: int = int(1e6)
    lista_inteiros: list[int] = [random.randint(1, LENGTH) for _ in range(LENGTH)]
    resultado_strings: list[str] = QuickSort.QuickSort(lista_strings.copy())
    resultado_int1: list[int] = QuickSort.QuickSort(lista_exemplo1)

    tempo_pivot_meio: float = measure_time(QuickSort.QuickSort, lista_inteiros.copy())
    tempo_pivot_primeiro: float = measure_time(
        QuickSort.QuickSort, lista_inteiros.copy() + [0]
    )

    print(f"Lista de strings antes: {lista_strings}")
    print(f"Lista de strings ordenada: {resultado_strings}")
    print(f"Lista númerica antes: {lista_exemplo1}")
    print(f"Lista numérica ordenada: {resultado_int1}\n\n")

    print("Com elementos repetidos: ")
    print(f"Tempo para QuickSort com pivô no meio: {tempo_pivot_meio} segundos")
    print(
        f"Tempo para QuickSort com pivô como primeiro elemento: {tempo_pivot_primeiro} segundos\n\n"
    )

    lista_inteiros = [x for x in range(LENGTH)]
    random.shuffle(lista_inteiros)
    tempo_pivot_meio: float = measure_time(QuickSort.QuickSort, lista_inteiros.copy())
    tempo_pivot_primeiro: float = measure_time(
        QuickSort.QuickSort, lista_inteiros.copy() + [0]
    )

    print("Com elementos repetidos: ")
    print(f"Tempo para QuickSort com pivô no meio: {tempo_pivot_meio} segundos")
    print(
        f"Tempo para QuickSort com pivô como primeiro elemento: {tempo_pivot_primeiro} segundos"
    )
