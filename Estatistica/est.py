import statistics as s
import matplotlib.pyplot as plt

ortopedia: list[int] = [
    119, 118, 125, 115, 107,
    128, 133, 133, 121, 101,
    118, 143, 125, 117, 141,
    109, 135, 115, 115, 119,
    131, 116, 115, 124, 134,
    140, 129, 129, 115, 119
]

pre_natal: list[int] = [
    108, 121, 120, 100, 97,
    128, 133, 133, 149, 111,
    122, 153, 125, 117, 141,
    119, 135, 115, 115, 119,
    131, 106, 115, 124, 120,
    140, 129, 129, 116, 121
]

dados: list[list[int]] = [ortopedia, pre_natal]


def coeficiente_variacao(dados: list[int]) -> float:
    media = s.mean(dados)
    desvio_padrao = s.stdev(dados)
    cv = (desvio_padrao / media) * 100
    return cv


nomes: list[str] = ["Ortopedia", "Pré-natal"]

for i, conjunto in enumerate(dados):
    print(f"Conjunto de dados: {nomes[i]}")
    print("-------------------------------")
    print(f"Moda: {s.mode(conjunto)}")
    print(f"Média: {s.mean(conjunto):.3f}")
    print(f"Mediana: {s.median(conjunto)}")
    print(f"Variância: {s.variance(conjunto):.3f}")
    print(f"Desvio Padrão: {s.stdev(conjunto):.3f}")
    print(f"Amplitude: {max(conjunto) - min(conjunto)}")
    print(
        f"Coeficiente de Variação (CV%): {coeficiente_variacao(conjunto):.3f}%")
    print(f'Total: {sum(conjunto)}')
    print("\n")


plt.figure(figsize=(8, 6))
plt.boxplot([ortopedia, pre_natal], labels=['Ortopedia', 'Pré-natal'])
plt.title('Boxplot dos conjuntos de dados')
plt.ylabel('Valores')
plt.grid(True)
plt.show()
