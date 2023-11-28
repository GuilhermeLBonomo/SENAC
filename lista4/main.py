from ArvoreBin import *


if __name__ == "__main__":
    tree1 = NodoArvore(10)
    tree1.add(5, side="left")
    tree1.add(15, side="right")

    tree2 = NodoArvore(10)
    tree2.add(5, side="left")
    tree2.add(15, side="right")

    print("Árvores idênticas:")
    print(tree1)
    print(tree2)
    print()

    is_identical = tree1.is_identical(tree2)
    print(f"As árvores são idênticas: {is_identical}")
    print()
    tree2.add(6)
    is_identical = tree1.is_identical(tree2)
    print(f"As árvores são idênticas: {is_identical}")
    print()

    smallest_element = tree1.find_min()
    print(f"Menor elemento na árvore: {smallest_element}")
    print()

    largest_element = tree1.find_max()
    print(f"Maior elemento na árvore: {largest_element}")
    print()

    height = tree1.calculate_height()
    print(f"Altura da árvore: {height}")
    print()

    is_balanced = tree1.is_balanced()
    print(f"A árvore é balanceada: {is_balanced}")
    print()
    is_balanced = tree2.is_balanced()
    print(f"A árvore é balanceada: {is_balanced}")
    print()

    tree1 = NodoArvore(10)
    tree1.add(5, side="left")
    tree1.add(5, side="right")
    is_symmetric = tree1.is_symmetric()
    print(f"A árvore é simétrica: {is_symmetric}")
    print()
    is_symmetric = tree2.is_symmetric()
    print(f"A árvore é simétrica: {is_symmetric}")
    print()
