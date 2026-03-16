import Modal from 'react-bootstrap/Modal';
import Button from 'react-bootstrap/Button';

function ModalTutorial({ tutorialP, onHandleClick })
{
    console.log("MODALTUTORIAL()");

    const handleClose = () => {
        onHandleClick();
    }

    return (
        <Modal show={true} onHide={handleClose} centered>
          <Modal.Header closeButton>
            <Modal.Title>{tutorialP.title}</Modal.Title>
          </Modal.Header>
          <Modal.Body>{tutorialP.allText}</Modal.Body>
          <Modal.Footer>
            <Button variant="secondary" onClick={handleClose}>
              Chiudi
            </Button>
          </Modal.Footer>
        </Modal>
    )
}

export default ModalTutorial;