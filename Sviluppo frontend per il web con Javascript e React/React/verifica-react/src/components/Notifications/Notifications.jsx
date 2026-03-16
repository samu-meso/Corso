import { useContext } from 'react';
import NotificationContext from './NotificationContext';
import Notification from './Notification';

export default function Notifications() {
  const { notifications } = useContext(NotificationContext);

  return (
    <div
      style={{
        position: 'fixed',
        top: '20px',
        right: '20px',
        zIndex: 9999,
        display: 'flex',
        flexDirection: 'column',
        gap: '10px',
        alignItems: 'flex-end'
      }}
    >
      {notifications.map((n) => (
        <Notification
          key={n.id}
          id={n.id}
          title={n.title}
          text={n.text}
          visible={n.visible}
          type={n.type}
          time={n.time}
        />
      ))}
    </div>
  );
}