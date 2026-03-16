import { useState } from "react";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import BookDetail from "./BookDetail";
import ModalTutorial from "./ModalTutorial";

function BookList({ bookList }) {

    const [selectedISBNBook, setSelectedISBNBook] = useState(null)


    const handleShowISBN = (value) => {
        console.log("BOOKLIST -> HANDLESHOW()")
        setSelectedISBNBook(value);
    };

    const handleClose = () => {
        setSelectedISBNBook(null);
    };

    const [openIndex, setOpenIndex] = useState(0);
    const [selectedBook, setselectedBook] = useState(bookList[0]);

    const handleShowBook = (book, index) => {
        if (openIndex === index) {
            setOpenIndex(null);
            setselectedBook(null);
            return;
        }
        setOpenIndex(index);
        setselectedBook(book);
    };

    return (
        <section>
            <Row className="mb-3">
                <Col>
                    <h2>Lista libri disponibili all'acquisto:</h2>
                </Col>
            </Row>
            <Row className="align-items-start">
                {/* SINISTRA */}
                <Col xs={12} lg={6}>
                    <Row xs="auto" className="g-2 align-items-stretch">
                        {bookList.map((book, index) => (
                            <Col key={index} className={index === 0 ? "ms-3" : "ms-5"}>
                                <BookDetail bookInfo={book} onHandleShow={handleShowISBN} onHandleShowBook={handleShowBook} index={index}
                                    isOpen={openIndex === index} />
                            </Col>
                        ))}
                    </Row>
                </Col>

                {/* DESTRA */}
                <Col xs={12} lg={6}>
                    <div className="p-3 border rounded bg-white shadow-sm">
                        {!selectedBook ? (
                            <div className="text-muted">Seleziona un libro per vedere i dettagli.</div>
                        ) : (
                            <>
                                <h4 className="mb-2">{selectedBook.title}</h4>
                                <div className="text-muted mb-3">Autore: {selectedBook.author}</div>

                                <div className="mb-3">{selectedBook.longDesc}</div>
                            </>
                        )}
                    </div>
                </Col>

                {selectedISBNBook ? <ModalTutorial
                    // showP={showS}
                    bookInfo={selectedISBNBook}
                    onHandleClick={handleClose} /> : undefined}
            </Row>
        </section>
    );
}

/*
Gestire una sorta di sito di una biblioteca dove ogni card è un libro con immagine, codice isbn, titolo ed editore. 
Far apparire una sezione all'interno della pagina (quindi non con la modale) 
in cui vengono esposti i dettagli del libro una volta che questo viene selezionato.
l caricamento della pagina questa deve visualizzare il primo libro tra le card.

collapse e fa vedere i dettagli. click su mostra isbn e ti apre un modal con l'immagine
*/
export default BookList;