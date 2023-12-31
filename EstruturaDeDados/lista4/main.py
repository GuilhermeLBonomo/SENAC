from ArvoreBin import NodoArvore


if __name__ == "__main__":
    tree = NodoArvore(5)
    tree.add(3)
    tree.add(7)
    tree.add(2)
    tree.add(4)
    tree.add(6)
    tree.add(8)
    print("Árvore original:")
    print(tree)
    print("\nPropriedades:")
    print(f"Altura da árvore: {tree.calculate_height()}")
    print(f"É balanceada? {tree.is_balanced()}")
    print(f"É simétrica? {tree.is_symmetric()}")
    print(f"Valor mínimo: {tree.find_min_or_max()}")
    print(f"Valor máximo: {tree.find_min_or_max(True)}")
    tree.remove(3)
    print("\nÁrvore após remover o valor 3:")
    print(tree)
    tree.add(1)
    print("\nÁrvore após adicionar o valor 1:")
    print(tree)
