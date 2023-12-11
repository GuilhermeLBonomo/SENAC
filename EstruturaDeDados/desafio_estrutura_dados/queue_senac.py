from flask import Flask, request, jsonify
from queue import Queue

# Alunos: Breno da Cunha, Guilherme Lopes, Matheus da Cunha, Milena Daflon

app = Flask(__name__)
queue = Queue()


@app.route("/enqueue", methods=["POST"])
def enqueue():
    data = request.json
    queue.put(data["element"])
    return jsonify({"message": "Element added to the queue"}), 200


@app.route("/dequeue", methods=["GET"])
def dequeue():
    if not queue.empty():
        element = queue.get()
        return jsonify({"element": element}), 200
    else:
        return jsonify({"message": "Queue is empty"}), 404


if __name__ == "__main__":
    app.run(debug=True)
