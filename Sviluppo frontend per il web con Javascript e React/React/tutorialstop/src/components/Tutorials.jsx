import { useState } from "react";
import Tutorial from "./Tutorial";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import ModalTutorial from "./ModalTutorial";
import Button from 'react-bootstrap/Button';

function Tutorials({ tutorialsP }) {
    console.log("TUTORIALS()");

    const [n, setN] = useState(0)

    // const [showS, setShowS] = useState(false);
    const [selectedTutorial, setSelectedTutorial] = useState(null)

    const handleShow = (tutorial) => {
        console.log("TUTORIALS -> HANDLESHOW()")
        // setShowS(true)
        setSelectedTutorial(tutorial)
    }

    const handleClose = () => {
        // setShowS(false)
        setSelectedTutorial(null)
    }

    function Add() {
        console.log("act is: " + n);
        // setN(n + 1)
        // setN(n + 1)
        setN((x) => x + 1)
        setN((d) => d + 1)
        setN((prev) => prev + 1)

        // setN(n => n + 1)
    }

    return (
        <section>
            {/* <Row>
                <Col><h2>Tutorial e Articoli</h2></Col>
                <Col>
                    <p>{n}</p>
                    <button onClick={Add}>Cliccami</button>
                </Col>
            </Row> */}
            <Row xs={2} md={4} lg={6}>
                {
                    tutorialsP.map((tutorial, index) => (
                        <Col key={index} className="flex-grow-1">
                            <Tutorial tutorialP={tutorial} onHandleShow={handleShow} />
                        </Col>
                    ))
                }
                <Col>
                    <Button variant="primary"
                        // onClick={handlePrevious}
                    >{"<"}</Button>
                    <Button variant="primary"
                        // onClick={handleNext}
                    >{">"}</Button>
                </Col>
            </Row>
            {selectedTutorial ? <ModalTutorial
                // showP={showS}
                tutorialP={selectedTutorial}
                onHandleClick={handleClose} /> : undefined}
        </section>
    )
}

export default Tutorials;