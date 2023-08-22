class Point1D:
    
    
    def __init__(self, x=0) -> None:
        self.x = point1D._valid_value(x)
        
        
    @property
    def x(self):
        return self._x


    @x.setter
    def x(self, value):
        self._x = point1D._valid_value(value)


    @staticmethod  
    def _valid_value(num: float) -> float:
        try:
            out = float(num)
        except:
            raise Exception('Invalid input.')
        return out
    

    def __str__(self) -> str:
        return f'X: {self.x}'
            
class Point2D:
    def __init__(self,x:float,y:float) -> None:
        self.__x = Point1D(x)
        self.__y = Point1D(y)
    def __str__(self) -> str:
        return f'(X:{self.__x};Y:{self.__y})'
    
    
if __name__ == '__main__':
    p = Point2D(100)
    print(p)