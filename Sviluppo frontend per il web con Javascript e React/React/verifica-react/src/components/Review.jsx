import Modal from 'react-bootstrap/Modal';
import Button from 'react-bootstrap/Button';

function Review({ show, handleClose, productInfo }) {
    return (
        <Modal show={show} onHide={handleClose} centered>
            <Modal.Header closeButton>
                <Modal.Title>Recensioni - {productInfo.title}</Modal.Title>
            </Modal.Header>

            <Modal.Body>
                {productInfo.reviews && productInfo.reviews.length > 0 ? (
                    productInfo.reviews.map((review, index) => (
                        <div key={index} style={{ marginBottom: '1rem' }}>
                            <strong>{review.reviewerName}</strong>
                            <div>
                                {Array.from({ length: 5 }, (_, i) =>
                                    i < Math.round(review.rating) ? '★' : '☆'
                                )}
                            </div>
                            <p className="mb-1">{review.comment}</p>
                            <small>{review.date}</small>
                            <hr />
                        </div>
                    ))
                ) : (
                    <p>Nessuna recensione disponibile.</p>
                )}
            </Modal.Body>

            <Modal.Footer className='justify-content-center'>
                <Button variant="secondary" onClick={handleClose}>
                    Chiudi
                </Button>
            </Modal.Footer>
        </Modal>
    );
}

export default Review;