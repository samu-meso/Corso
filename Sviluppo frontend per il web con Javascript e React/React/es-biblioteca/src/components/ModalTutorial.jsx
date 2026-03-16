import Modal from 'react-bootstrap/Modal';
import Button from 'react-bootstrap/Button';

function ModalTutorial({ bookInfo, onHandleClick }) {
  console.log("MODALTUTORIAL()");

  const handleClose = () => onHandleClick();
  return (
    <Modal show={true} onHide={handleClose} centered>
      <Modal.Header closeButton>
        <Modal.Title>Titolo: <i>{bookInfo.title}</i></Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <img src={bookInfo.isbn} style={{ width: "350px", height: "150px", objectFit: "cover" }} className='w-5'></img>
      </Modal.Body>
      <Modal.Footer>
        <Button variant="secondary" onClick={handleClose}>
          Chiudi
        </Button>
      </Modal.Footer>
    </Modal>
  )
}

export default ModalTutorial;