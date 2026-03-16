
import Button from 'react-bootstrap/Button';
import Card from 'react-bootstrap/Card';
import ListGroup from 'react-bootstrap/ListGroup';


function BookDetail({ bookInfo, onHandleShow, onHandleShowBook, isOpen, index }) {

    const handleShowISBN = () => {
        console.log("BOOK DETAIL -> HANDLESHOW()")
        onHandleShow(bookInfo)
    }

    const handleShowBook = () => {
        onHandleShowBook(bookInfo, index)
    }

    return (
        <Card style={{ width: '18rem' }}>
            <Card.Img variant="top" src={bookInfo.img} />
            <Card.Body>
                <Card.Title>{bookInfo.title}</Card.Title>
                <Card.Text>
                    {bookInfo.desc}
                </Card.Text>
            </Card.Body>
            <ListGroup className="list-group-flush">
                <ListGroup.Item>Autore: <i>{bookInfo.author}</i></ListGroup.Item>
            </ListGroup>
            <Card.Body>
                <Button variant="primary" className='ms-4' onClick={handleShowBook}>{isOpen  ? "Chiudi" : "Altre info"}</Button>
                <Button variant="primary" className='ms-4' onClick={handleShowISBN}>ISBN</Button>
            </Card.Body>
        </Card>
    );
}

export default BookDetail;