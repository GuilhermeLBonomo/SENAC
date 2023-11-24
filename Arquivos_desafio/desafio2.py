from re import search
from pathlib import Path
from datetime import datetime

path_alvo: Path = Path(__file__).resolve().parent / "desafio_estrutura_dados/main.py"


def atualizar_path_alvo() -> None:
    global path_alvo
    novo_path = input(
        f"Digite o path absoluto do arquivo .py que deseja escanear \n\n[Path padrão: {path_alvo}]"
    )
    if novo_path.strip():
        path_alvo = Path(novo_path)


def pegar_arquivo(arquivo=path_alvo) -> list:
    with open(arquivo, "r", encoding="UTF-8") as file:
        return file.readlines()


def pegar_cript(txt: str) -> str:
    frase = txt[::-1]
    if "," in frase:
        frase = frase[: frase.index(",")]
    frase = frase[::-1]
    match_contenido = search(r"['\"](.*?)['\"]", frase)
    if match_contenido:
        return match_contenido.group(1)
    return ""


def extrair_valores_insert(arquivo) -> dict:
    resultados = {".left": None, ".right": None}
    linhas_do_arquivo = pegar_arquivo()
    for i, linha in enumerate(linhas_do_arquivo):
        if "insert" in linha:
            value = pegar_cript(linha)
            for j in range(i - 1, -1, -1):
                linha_anterior = linhas_do_arquivo[j].strip()
                match_node = search(r"\bnode\.(right|left)\b", linha_anterior)
                if match_node:
                    tipo_node = match_node.group(1)
                    resultados[f".{tipo_node}"] = value
                    break
    return resultados


def encontrar_chave_por_valor(dicionario, valor):
    for chave, valor_atual in dicionario.items():
        if valor_atual == valor:
            return chave
    raise ValueError(f"Valor {valor} não encontrado no dicionário")


def decript_dic(txt: str, dicionario_enc: dict[str, str]) -> list:
    txt = txt.replace("  ", " $ ")  # escolhi $ como espaço em branco
    if isinstance(dicionario_enc, list):
        return decript_list(txt, dicionario_enc)
    out = []
    for linha in txt.split():
        arvore = "tree"
        for value in linha:
            if value == "$":
                arvore = " "
            else:
                arvore += (
                    f"/{encontrar_chave_por_valor(dicionario_enc, value)}".replace(
                        "/", ""
                    )
                )
        out.append(arvore)
    return out


def decript_list(txt: str, lista_dic: list[dict[str, str]]) -> list:
    if isinstance(lista_dic, dict):
        return decript_dic(txt, lista_dic)
    out = [decript_dic(txt, dic) for dic in lista_dic]
    return out


def arvore_to_char(arvore: str) -> str:
    if arvore == " ":
        return arvore
    linhas_do_arquivo = pegar_arquivo()
    for linha in linhas_do_arquivo:
        if search(rf"\b{arvore}\b(?!\.left|\.right)", linha):
            valor_nodo = search(r'(?<=\bNode\(")(.*?)(?="\))', linha)
            return valor_nodo.group(1)
    return ""


def traduzir(codigos: list[str]) -> str:
    print(
        "Devido à criptografia original não ser case-sensitive, a saída será em maiúsculas."
    )
    out = ""
    for cod in codigos:
        out += arvore_to_char(cod)
    return out


def main_script() -> None:
    ti = datetime.now()
    valores = extrair_valores_insert(path_alvo)
    if None in valores.values():
        raise ValueError("Valores não encontrados no arquivo.")
    inp = input("Digite a mensagem criptografada: ")
    arvores = decript_list(inp, valores)
    tf = datetime.now()
    print(f"\n\nMensagem descriptografada em {tf-ti}: \n{traduzir(arvores)}")


if __name__ == "__main__":
    atualizar_path_alvo()
    main_script()
