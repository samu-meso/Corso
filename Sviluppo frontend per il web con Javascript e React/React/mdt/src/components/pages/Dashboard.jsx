import { useContext } from "react";
import { sectionContext } from "../store/sectionContext";
import NotificationContext from "../notifications/NotificationContext"
import Notifications from "../notifications/Notifications";
import { useEffect } from "react";

function Dashboard() {
    const { currentSection, setCurrentSection } = useContext(sectionContext);
    const { addNotification, removeNotification } = useContext(NotificationContext);

    useEffect(() => {
        addNotification("Test", "Notifica caricata");
    }, []);
    return (
        <>
            <h1>Creating MDT from scratch</h1>
            <Notifications />
            <button onClick={() => addNotification("profiles", "caricaata")}>Test</button>
        </>
    )
}

export default Dashboard;