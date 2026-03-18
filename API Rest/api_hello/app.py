from flask import Flask, jsonify, render_template
import sys

app = Flask(__name__)


@app.route("/", methods=["GET"])
def homepage():
    return render_template("index.html")  


@app.route("/helloworld", methods=["GET"])
def helloworld():
    return "Hello World"


# Nuova rotta che restituisce JSON
@app.route("/api/hello", methods=["GET"])
def hello_json():
    data = {"message": "Hello from the API!", "status": "ok"}
    return jsonify(data)

@app.route("/api/info", methods=["GET"])
def info_json():
    data = {
        "nome_corso": "Corso di Py Flask API Rest",
        "versione_python": f"{sys.version_info.major}.{sys.version_info.minor}.{sys.version_info.micro}",
        "numero_studenti": 25,
    }
    return jsonify(data)

@app.route("/api/greet/<nome>/<cognome>", methods=["GET"])
def greet_json(nome, cognome):
    data = {
        "message": f"Saludo {nome}, {cognome}"
    }
    return jsonify(data)


@app.route("/api/hello/<name>", methods=["GET"])
def hello_name(name):
    return jsonify({"message": f"Hello, {name}!"})


if __name__ == "__main__":
    app.run(debug=True, port=5001)
