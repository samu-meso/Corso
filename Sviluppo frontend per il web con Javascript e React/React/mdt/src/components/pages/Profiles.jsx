import { useContext } from "react";
import { sectionContext } from "../store/sectionContext";


function Profiles() {
    const { currentSection, setCurrentSection } = useContext(sectionContext);


    return (
        <>
            <h1>SOFI SEI LA MIA VITA</h1>
            <button onClick={() => setCurrentSection("dashboard")}>Cambia sezione</button>
        </>
    )
}

export default Profiles;