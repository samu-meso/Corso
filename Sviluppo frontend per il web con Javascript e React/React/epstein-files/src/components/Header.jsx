import ButtonGroup from 'react-bootstrap/ButtonGroup';
import Dropdown from 'react-bootstrap/Dropdown';
import DropdownButton from 'react-bootstrap/DropdownButton';
import './Header.css'
// import { useState } from 'react';

const menus = [
    {
        title: "Files",
        submenus: ["Immagini", "Email"],
    },
]

function Header() {

    // const [page, setPage] = useState(null)
    return (
        <>
            <h1>Epstein Files</h1>
            {
                menus.map((variant) => (
                    <DropdownButton
                        as={ButtonGroup}
                        key={variant.title}
                        id={`dropdown-variants-${variant.title}`}
                        variant="primary"
                        title={variant.title}
                        className='dropdown'
                    >
                        {
                            variant.submenus.map((menu, index) => (
                                <Dropdown.Item eventKey={`${menu}-${index}`}>{menu}</Dropdown.Item>
                            ))
                        }
                    </DropdownButton>
                ))
            }
        </>
    );
}

export default Header;