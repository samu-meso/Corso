import { useState } from "react";
import NotificationContext from './NotificationContext';

function NotificationProvider({ children }) {
    const [notifications, setNotifications] = useState([])

    const addNotification = (title, text) => {
        const id = Date.now() + Math.random();

        const newNotification = {
            id,
            title,
            text
        };

        setNotifications((prev) => [...prev, newNotification]);

        setTimeout(() => {
            setNotifications((prev) =>
                prev.map((n) =>
                    n.id === id ? { ...n, visible: true } : n
                )
            );
        }, 10);

        return id;
    };
    const removeNotification = (id) => {
        setNotifications((prev) =>
            prev.map((n) =>
                n.id === id ? { ...n, visible: false } : n
            )
        );
    };

    return (
        <NotificationContext.Provider value={{
            notifications,
            addNotification,
            removeNotification,
        }}
        >
            {children}
        </NotificationContext.Provider>
    )
}

export default NotificationProvider;