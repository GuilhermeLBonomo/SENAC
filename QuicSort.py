class QuickSort:
    @staticmethod
    def QuickSortStr(arr: list, pivot) -> list:
        out = []
        for value in arr:
            out.append(ord(str(value)))
        out = QuickSort.QuickSortNum(out, ord(str(pivot)))
        for value in arr:
            out.append(chr(value))
        return out

    @staticmethod
    def QuickSortNum(arr: list[int], pivot: int) -> list[int]:
        if pivot not in arr:
            raise ValueError('Erro: valor do pivot não está contido no array')
        arr_esq = [value for value in arr if value < pivot]
        arr_dir = [value for value in arr if value > pivot]
        out = arr_esq + [pivot] + arr_dir
        return out


if __name__ == "__main__":
    arr1 = [3, 8, 7, 10, 0, 23, 2, 1, 77, 7]
    pv1 = 3
    arr2 = ["3", "8", "7", "10", "0", "23", "2", "1", "77", "7"]
    pv2 = "3"
    print(QuickSort.QuickSortStr(arr2, pv2))
