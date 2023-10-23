def inverter(txt: str):
    tamanho = len(txt)
    if tamanho == 1:
        return txt
    else:
        return txt[tamanho - 1] + inverter(txt[:-1])


def palindromo(txt: str) -> bool:
    palavra = txt.lower()
    arvalap = inverter(palavra)
    return True if palavra == arvalap else False


if __name__ == "__main__":
    palavra = input("Digite uma palavra: ")
    if palindromo(palavra):
        print(f"'{palavra}' é um palíndromo")
    else:
        print(f"'{palavra}' não é um palíndromo")
