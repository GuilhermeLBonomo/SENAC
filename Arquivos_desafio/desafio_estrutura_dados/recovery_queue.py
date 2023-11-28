import requests

# Alunos: Breno da Cunha, Guilherme Lopes, Matheus da Cunha, Milena Daflon


def get_from_queue() -> None:
    response = requests.get("http://127.0.0.1:5000/dequeue", timeout=5)
    print(response.json())


if __name__ == "__main__":
    get_from_queue()
