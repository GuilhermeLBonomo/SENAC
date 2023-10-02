class Macaco:
    def __init__(self, nome: str) -> None:
        self.__nome = nome.capitalize()
        self.__bucho = []
    
    def comer(self, comida: str) -> None:
        self.__bucho.append(comida)
    
    def ver_bucho(self) -> None:
        print(str(self))
    
    def digerir(self, tudo=True) -> None:
        if tudo:
            self.__bucho.clear()
        else:
            self.__bucho.pop(0)
        
    def __str__(self) -> str:
        return (f'Nome: {self.__nome}\n'f'Bucho: {self.__bucho}')

if __name__ == '__main__':
    m1 = Macaco('0001')
    m2 = Macaco('0002')
    m3 = Macaco('Canibal')
    m1.comer('banana')
    m1.comer('maçã')
    m2.comer('banana')
    m2.comer('banana')
    m2.comer('banana')
    m2.comer('banana')
    print(m1)
    m2.ver_bucho()
    m3.comer(m2)
    print(m3)
