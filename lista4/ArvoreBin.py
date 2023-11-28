class NodoArvore:
    def __init__(self, value=None) -> None:
        self.value = value
        self._left = None
        self._right = None

    def __str__(self) -> str:
        left_str = str(self.left) if self.left else "None"
        right_str = str(self.right) if self.right else "None"
        return f"NodoArvore(value={self.value}, left={left_str}, right={right_str})"

    @property
    def left(self):
        return self._left

    @left.setter
    def left(self, node) -> None:
        self._left = node

    @property
    def right(self):
        return self._right

    @right.setter
    def right(self, node) -> None:
        self._right = node

    def add(self, value, side="left") -> None:
        new_node = NodoArvore(value)
        if side == "left":
            self.left = new_node
        elif side == "right":
            self.right = new_node

    def remove(self, side="left") -> None:
        if side == "left":
            self.left = None
        elif side == "right":
            self.right = None

    def insert(self, value: int, side: str = "left") -> None:
        if side not in ["left", "right"]:
            raise ValueError("O lado deve ser 'left' ou 'right'")
        new_node: "NodoArvore" = NodoArvore(value)
        if side == "left":
            self.left = new_node
        else:
            self.right = new_node

    def find_min(self):
        current = self
        while current.left is not None:
            current = current.left
        return current.value

    def find_max(self):
        current = self
        while current.right is not None:
            current = current.right
        return current.value

    def is_identical(self, other) -> bool:
        if self is None and other is None:
            return True
        elif self is not None and other is not None:
            values_are_equal = self.value == other.value
            left_is_identical = (
                self.left.is_identical(other.left) if self.left and other.left else True
            )
            right_is_identical = (
                self.right.is_identical(other.right)
                if self.right and other.right
                else True
            )
            return values_are_equal and left_is_identical and right_is_identical
        return False

    def calculate_height(self) -> int:
        if self is None:
            return 0
        else:
            left_height = self.left.calculate_height() if self.left else 0
            right_height = self.right.calculate_height() if self.right else 0
            return max(left_height, right_height) + 1

    def is_balanced(self) -> bool:
        def check_balance(node):
            if node is None:
                return True, 0
            left_balanced, left_height = check_balance(node.left)
            right_balanced, right_height = check_balance(node.right)
            balanced = (
                left_balanced
                and right_balanced
                and abs(left_height - right_height) <= 1
            )
            height = max(left_height, right_height) + 1

            return balanced, height

        balanced, _ = check_balance(self)
        return balanced

    def is_symmetric(self) -> bool:
        def is_mirror(node1, node2):
            if node1 is None and node2 is None:
                return True
            if node1 is not None and node2 is not None:
                return (
                    node1.value == node2.value
                    and is_mirror(node1.left, node2.right)
                    and is_mirror(node1.right, node2.left)
                )
            return False

        return is_mirror(self.left, self.right)
