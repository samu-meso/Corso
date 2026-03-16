import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import ProgressBar from 'react-bootstrap/ProgressBar';
import Button from 'react-bootstrap/Button';
import { useState } from "react";


export default function Interview({ interviews }) {
    const [interview, setInterview] = useState(0);
    const now = ((interview + 1) / interviews.length) * 100;

    const handlePrevious = () => {
        setInterview((prev) => {
            if (prev > 0) {
                return prev - 1;
            }
            if (prev == 0) {
                return interviews.length - 1
            }
            return prev;
        });
    }
    const handleNext = () => {
        setInterview((prev) => {
            if (prev < interviews.length - 1) {
                return prev + 1;
            }
            if (prev == interviews.length - 1) {
                return 0
            }
            return prev;
        });
    }

    return (
        <>
            <section>
                <Row>
                    <Col xs="12">
                        <p>La posizione dell'intervista</p>
                        <ProgressBar now={now} label={`${now}%`} />
                    </Col>
                    <Col className="ms-2">
                        {interviews[interview] != null ? <>
                            <h2>{interviews[interview].title}</h2>
                            <p>{interviews[interview].text}</p>
                        </> : undefined}
                    </Col>
                    <Col className="mt-4">
                        <Button variant="primary"
                            onClick={handlePrevious}
                        >Previuos</Button>
                        <Button variant="primary"
                            onClick={handleNext}
                        >Next</Button>
                    </Col>

                </Row>
            </section>
        </>
    )
}