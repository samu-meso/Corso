import Row from 'react-bootstrap/Row';
import Container from 'react-bootstrap/Container';
import Product from './Product';

export default function Products({ products, pLoading }) {
    console.log("PRODUCTS()")
    return (
        <Container fluid>
            <Row xs={2} md={4} lg={6} className="align-items-center justify-content-center">
                {products.length > 0 ? (
                    products.map((n) => (
                        <Product key={n.id} productInfo={n}/>
                    ))
                ) : (
                    pLoading ? <p style={{width: "max-content"}}>Caricamento prodotti in corso..</p> : <p style={{width: "max-content"}}>Nessun prodotto disponibile..</p>
                )}
            </Row>
        </Container>
    );
}