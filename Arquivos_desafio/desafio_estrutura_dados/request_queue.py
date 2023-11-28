import requests

# Alunos: Breno da Cunha, Guilherme Lopes, Matheus da Cunha, Milena Daflon


def add_to_queue(element) -> None:
    response = requests.post(
        "http://127.0.0.1:5000/enqueue", json={"element": element}, timeout=5
    )
    print(response.json())


if __name__ == "__main__":
    element = input("Enter an element to add to the queue: ")
    add_to_queue(element)
