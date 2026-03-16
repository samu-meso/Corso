import { useContext } from 'react';
import Toast from 'react-bootstrap/Toast';
import Spinner from 'react-bootstrap/Spinner';
import logo from '../../assets/react.svg';
import NotificationContext from './NotificationContext';
import './notification.css';
import { CheckCircleFill } from 'react-bootstrap-icons';

export default function Notification({ id, title, text, visible, type, time }) {
  const { removeNotification } = useContext(NotificationContext);

  const getTimeAgo = (time) => {
    const diff = Math.floor((Date.now() - new Date(time)) / 1000);

    if (diff < 60) {
      return `${diff} sec fa`;
    }

    if (diff < 3600) {
      return `${Math.floor(diff / 60)} min fa`;
    }

    return `${Math.floor(diff / 3600)} h fa`;
  };

  return (
    <Toast
      onClose={() => removeNotification(id)}
      className={`custom-toast ${visible ? 'show-toast' : 'hide-toast'}`}
    >
      <Toast.Header closeButton>
        <img
          src={logo}
          className="rounded me-2"
          alt=""
          style={{ width: '20px', height: '20px' }}
        />

        <strong className="me-auto">{title}</strong>

        <small>
          {time ? getTimeAgo(time) : ''}
        </small>
      </Toast.Header>

      <Toast.Body className="d-flex align-items-center gap-2">
        {type === 'loading' && (
          <Spinner animation="border" size="sm" />
        )}

        {type === 'success' && (
          <CheckCircleFill size={18} color="green" />
        )}

        {text}
      </Toast.Body>
    </Toast>
  );
}