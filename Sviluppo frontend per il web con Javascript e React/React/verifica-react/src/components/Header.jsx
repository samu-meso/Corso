import Container from 'react-bootstrap/Container';
import Image from 'react-bootstrap/Image';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import NavDropdown from 'react-bootstrap/NavDropdown';
import Offcanvas from 'react-bootstrap/Offcanvas';
import logo from '../assets/react.svg';
import './header.css';

export default function Header({ onRefresh }) {
    return (
        <Navbar
            className="custom-navbar"
            variant="dark"
            expand={false}
        >
            <Container fluid>
                <Navbar.Brand className="d-flex align-items-center gap-2">
                    <Image
                        src={logo}
                        thumbnail
                        style={{ width: '40px', height: '40px' }}
                    />

                    <h4 className="m-0 custom-brand-title">
                        E-commerce
                    </h4>
                </Navbar.Brand>

                <Navbar.Toggle
                    aria-controls="offcanvasNavbar"
                    className="custom-toggle"
                />

                <Navbar.Offcanvas
                    id="offcanvasNavbar"
                    placement="end"
                    className="custom-offcanvas"
                >
                    <Offcanvas.Header closeButton className="custom-offcanvas-header">
                        <Offcanvas.Title className="custom-offcanvas-title">
                            Menu
                        </Offcanvas.Title>
                    </Offcanvas.Header>

                    <Offcanvas.Body className="custom-offcanvas-body">
                        <Nav className="flex-column w-100 gap-2">
                            <NavDropdown
                                title="Dev"
                                id="basic-nav-dropdown"
                                className="custom-dropdown"
                            >
                                <NavDropdown.Item onClick={onRefresh}>
                                    Ricarica prodotti
                                </NavDropdown.Item>
                            </NavDropdown>
                        </Nav>
                    </Offcanvas.Body>
                </Navbar.Offcanvas>
            </Container>
        </Navbar>
    );
}