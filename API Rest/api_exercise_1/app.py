from flask import Flask, jsonify, render_template

app = Flask(__name__)


@app.route("/", methods=["GET"])
def homepage():
    return render_template("index.html")  # Flask cerca in templates/


@app.route("/api/info", methods=["GET"])
def info_json():
    return jsonify(
        {"nome_corso": "api_rest", "versione_python": "3.14.3", "numero_studenti": 20}
    )


@app.route("/api/greet/<nome>/<cognome>", methods=["GET"])
def greet_json(nome, cognome):
    return jsonify({"message": f"Hello, {nome + " " + cognome}!"})


if __name__ == "__main__":
    app.run(debug=True, port=5001)
