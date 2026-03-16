import Notification from "./Notification";
import { useContext } from "react";
import NotificationContext from "./NotificationContext";

function Notifications() {
    const { notifications } = useContext(NotificationContext);

    return (
        <>
            {
                notifications.map((notify, index) =>
                    <Notification key={index} info={notify} />
                )
            }
        </>
    )
}

export default Notifications;