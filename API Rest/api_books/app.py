from flask import Flask, jsonify, render_template
from models.book import Book
from controllers.book_controller import book_controller
from fake_database import load_books
from flask_cors import CORS

app = Flask(__name__)
app.register_blueprint(
    book_controller
)  # serve per registrare il blueprint del controller, in questo modo tutte le rotte definite in book_controller saranno disponibili nell'app
CORS(app)


@app.route("/", methods=["GET"])
def homepage():
    return render_template("home.html")  # Flask cerca in templates/


if __name__ == "__main__":
    load_books()
    app.run(debug=True, port=5001)
