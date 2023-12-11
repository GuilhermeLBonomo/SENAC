class bombaCombustivel:
    def __init__(self, tipoCombustivel: str, valorLitro: float, quatidadeCombustivel=0.0) -> None:
        self.__tipoCombustivel = tipoCombustivel.capitalize()
        self.__valorLitro = 0.0
        self.alterarValor(valorLitro)
        self.__quatidadeCombustivel = 0.0
        self.alterarQuantidade(quatidadeCombustivel)
    
    def abastecerPorValor(self, valor: float) -> None:
        litros = valor / self.__valorLitro
        print(f'Custou:  {litros/self.__valorLitro} R$')
        self.alterarQuantidade(litros)
    
    def abastecerPorlitro(self, litro: float) -> None:
        preco = litro * self.__valorLitro
        print(f'Custou: {preco} R$')
        self.alterarQuantidade(litro)
    
    
    def alterarValor(self, valor: float) -> None:
        try:
            new_value = float(valor)
            if new_value <= 0:
                raise ValueError
            
        except ValueError as e:
            raise ValueError from e
        self.__valorLitro = new_value
    
    def alterarCombustivel(self, tipo: str) -> None:
        self.__tipoCombustivel = tipo
    
    def alterarQuantidade(self, valor: float) -> None:
        try:
            new_value = float(valor)
            if new_value <= 0:
                raise ValueError
            
        except ValueError as e:
            raise ValueError from e
        self.__quatidadeCombustivel = new_value
    
    def __str__(self) -> None:
        return f'Tipo de Combustível: {self.__tipoCombustivel}\n'f'Valor do Litro: {self.__valorLitro} R$\n'f'Quantidade de Combustível: {self.__quatidadeCombustivel} L\n'

if __name__ == '__main__':
    b1 = bombaCombustivel('diesel', 7.99, 90)
    print(b1)