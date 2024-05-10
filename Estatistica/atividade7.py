import pandas as pd
import matplotlib.pyplot as plt

dados: list[list[int]] = [
    [5, 7, 12, 7, 9, 6, 4, 3, 8, 13],
    [13, 5, 7, 9, 11, 5, 12, 10, 4, 15],
    [5, 16, 6, 5, 5, 8, 9, 5, 6, 10],
    [9, 5, 4, 9, 10, 6, 6, 4, 5, 6],
    [5, 14, 15, 7, 4, 26, 9, 13, 8, 7],
    [6, 4, 5, 4, 5, 5, 11, 8, 9, 7]
]


def formatar_dados(dados: list[list[int]]) -> pd.DataFrame:
    if dados is None:
        raise ValueError("Dados não podem ser None")
    if not all(len(sublista) == 10 for sublista in dados):
        raise ValueError("Todas as sublistas devem ter tamanho 10")

    df: pd.DataFrame = pd.DataFrame(
        dados, columns=[f'Dado{i+1}' for i in range(10)])
    indices = pd.Index([f'{i} Classe' for i in range(
        1, len(dados) + 1)], name='Classes i')
    df.index = indices

    df['Li'] = df.iloc[:, :].min(axis=1)
    df['LS'] = df.iloc[:, :].max(axis=1)

    df['Tempo (min e max)'] = df['Li'].astype(
        str) + '—' + df['LS'].astype(str) + ' min'
    df['Xi'] = df.select_dtypes(include='number').mean(axis=1)
    df['fi'] = df.count(axis=1) - 1

    df['%fr'] = df['fi'] / df['fi'].sum() * 100
    df['fac'] = df['fi'].cumsum()
    df['%frc'] = df['fac'] / df['fi'].sum() * 100
    df['fad'] = df['fac'].shift(-1).fillna(0)
    df['%frd'] = df['fad'] / df['fi'].sum() * 100

    df = df.drop(columns=[f'Dado{i+1}' for i in range(10)])

    return df


def plotar_histograma(dados: list[list[int]]) -> None:
    flat_data: list[int] = [item for sublist in dados for item in sublist]
    plt.hist(flat_data, bins=range(3, 28, 3), edgecolor='black')
    plt.title('Histograma de Tempo de Pintura')
    plt.xlabel('Tempo (minutos)')
    plt.ylabel('Frequência')
    plt.grid(True)
    plt.xticks(range(3, 28, 3))
    plt.show()


def plotar_ogiva_galton(dados: list[list[int]]) -> None:
    flat_data: list[int] = sorted(
        [item for sublist in dados for item in sublist])
    freq_acumulada: range = range(1, len(flat_data) + 1)
    plt.plot(flat_data, freq_acumulada, marker='o', linestyle='-')
    plt.title('Ogiva de Galton')
    plt.xlabel('Tempo (minutos)')
    plt.ylabel('Frequência acumulada')
    plt.grid(True)
    plt.xticks(range(3, 28, 3))
    plt.show()


if __name__ == '__main__':
    df: pd.DataFrame = formatar_dados(dados)
    df.to_excel('tabela_excel.xlsx', float_format='%.3f')

    plotar_histograma(dados)
    plotar_ogiva_galton(dados)
