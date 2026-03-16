import Toast from 'react-bootstrap/Toast';

function Notification({ info }) {
    return (
        <Toast>
            <Toast.Header>
                <img src="holder.js/20x20?text=%20" className="rounded me-2" alt="" />
                <strong className="me-auto">{info.title}</strong>
                <small>11 mins ago</small>
            </Toast.Header>
            <Toast.Body>{info.text}</Toast.Body>
        </Toast>
    )
}

export default Notification; 