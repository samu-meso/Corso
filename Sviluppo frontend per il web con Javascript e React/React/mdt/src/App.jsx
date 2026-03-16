import Dashboard from "./components/pages/Dashboard";
import 'bootstrap/dist/css/bootstrap.min.css';
import { useState } from "react";
import { sectionContext } from "./components/store/sectionContext";
import NotificationProvider from "./components/notifications/NotificationProvider";


function App() {
    const [currentSection, setCurrentSection] = useState("dashboard");


    return (
        <sectionContext.Provider value={{ currentSection, setCurrentSection }}>
            <NotificationProvider>
                <Dashboard></Dashboard>
            </NotificationProvider>
            {/* {currentSection === 'dashboard' && <NotificationProvider><Dashboard /></NotificationProvider>}
            {currentSection === 'profiles' && <Profiles />} */}
        </sectionContext.Provider>
    );
}

export default App;