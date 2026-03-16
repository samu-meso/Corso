import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import { useState } from 'react';

function FormTutorials({ tutorialsP, onAddComment }) {

    console.log("FORMTUTORIALS()")

    const [choice, setChoice] = useState(0);
    const [test, setTest] = useState("");

    function handleSumbit(e) {
        e.preventDefault();
        console.log("handleSumbit()")
        // devo creare un oggetto per recuperare il titolo selezionato e il commento scritto.
        // il tutto per aggiornare una lista di commenti
        const comment = {
            id: Date.now(),
            tutorialTitle: tutorialsP[choice].title,
            text: test
        }
        onAddComment(comment)
    }

    function handleChoiceChange(e) {
        console.log(e.target.value)
        setChoice(+e.target.value)
    }

    function handleChange(e) {
        console.log(e.target.value)
        setTest(e.target.value)
    }

    return (
        <Form className="row border rounded" onSubmit={handleSumbit}>
            <Form.Group className="col-xs-12 col-sm-6 col-md-4 mb-3" controlId="tutorialSelect">
                <Form.Label>Secegli il tutorial</Form.Label>
                <Form.Select value={choice} onChange={handleChoiceChange}>
                    {
                        tutorialsP.length > 0
                            ?
                            tutorialsP.map((tutorial, index) => (
                                <option value={index} key={index}>{tutorial.title}</option>
                            ))
                            :
                            <option value={0} key={0}>Non ci sono tutorial disponibili.</option>
                    }
                    {/* <option>Open this select menu</option>
                    {
                        tutorialsP.map((tutorial, index) => (
                            <option value={index} key={index}>{tutorial.title}</option>
                        ))
                    } */}
                </Form.Select>
            </Form.Group>
            <Form.Group className="col-xs-12 col-sm-6 col-md-4 mb-3" controlId="tutorialText">
                <Form.Label>Scrivi il commento</Form.Label>
                <Form.Control type="text" value={test} onChange={handleChange} />

            </Form.Group>
            <Form.Group className="col-xs-12 mb-3">
                <Button type="submit">Invia</Button>
            </Form.Group>
        </Form>
    )
}

export default FormTutorials;