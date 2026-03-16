import { useState } from 'react';
import NotificationContext from './NotificationContext';

export default function NotificationProvider({ children }) {
  const [notifications, setNotifications] = useState([]);

  const addNotification = (title, text, options = {}) => {
    const id = Date.now() + Math.random();

    const newNotification = {
      id,
      title,
      text,
      visible: false,
      type: options.type || 'info',
      persistent: options.persistent || false,
      time: options.time || new Date()
    };

    setNotifications((prev) => [...prev, newNotification]);

    setTimeout(() => {
      setNotifications((prev) =>
        prev.map((n) =>
          n.id === id ? { ...n, visible: true } : n
        )
      );
    }, 10);

    if (!newNotification.persistent) {
      setTimeout(() => {
        removeNotification(id);
      }, 5000);
    }

    return id;
  };

  const removeNotification = (id) => {
    setNotifications((prev) =>
      prev.map((n) =>
        n.id === id ? { ...n, visible: false } : n
      )
    );

    setTimeout(() => {
      setNotifications((prev) => prev.filter((n) => n.id !== id));
    }, 2500);
  };

  const updateNotification = (id, updates) => {
    setNotifications((prev) =>
      prev.map((n) =>
        n.id === id ? { ...n, ...updates } : n
      )
    );
  };

  return (
    <NotificationContext.Provider
      value={{
        notifications,
        addNotification,
        removeNotification,
        updateNotification
      }}
    >
      {children}
    </NotificationContext.Provider>
  );
}