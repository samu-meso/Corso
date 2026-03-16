import Card from 'react-bootstrap/Card';
import Button from 'react-bootstrap/Button';
import { useState } from 'react';
import Review from './Review';

function Product({ productInfo }) {
    const [showModal, setShowModal] = useState(false);

    return (
        <>
            <Card
                className="shadow-sm"
                style={{
                    width: '14rem',
                    height: '470px',
                    margin: '8px',
                    borderRadius: '12px',
                    overflow: 'hidden',
                    border: '1px solid #d1d5db'
                }}
            >
                <Card.Img
                    variant="top"
                    src={productInfo.images[0]}
                    style={{
                        height: '160px',
                        objectFit: 'contain',
                        padding: '12px',
                        backgroundColor: '#fff'
                    }}
                />

                <Card.Body className="d-flex flex-column p-3">

                    <small
                        className="text-muted mb-1"
                        style={{ fontSize: '0.75rem' }}
                    >
                        Sponsorizzato
                    </small>

                    <Card.Title
                        style={{
                            fontSize: '0.95rem',
                            fontWeight: '500',
                            lineHeight: '1.3',
                            minHeight: '50px',
                            display: '-webkit-box',
                            WebkitLineClamp: 2,
                            WebkitBoxOrient: 'vertical',
                            overflow: 'hidden'
                        }}
                    >
                        {productInfo.title}
                    </Card.Title>

                    <Card.Title
                        style={{
                            fontSize: '0.55rem',
                            // fontWeight: '500',
                            lineHeight: '1.3',
                            minHeight: '50px',
                            display: '-webkit-box',
                            WebkitLineClamp: 2,
                            WebkitBoxOrient: 'vertical',
                            overflow: 'hidden'
                        }}
                    >
                        {productInfo.description}
                    </Card.Title>

                    <div
                        className="d-flex align-items-center gap-1 mb-2"
                        style={{ fontSize: '0.8rem' }}
                    >
                        <span>{productInfo.rating}</span>

                        <span style={{ color: '#f59e0b' }}>
                            {Array.from({ length: 5 }, (_, i) =>
                                i < Math.round(productInfo.rating) ? '★' : '☆'
                            )}
                        </span>

                        <span style={{ color: '#2563eb' }}>
                            ({productInfo.reviews?.length || 0})
                        </span>
                    </div>

                    <div className="mb-1">
                        <span
                            style={{
                                fontSize: '1.35rem',
                                fontWeight: '600'
                            }}
                        >
                            {productInfo.price}
                        </span>
                        <span style={{ fontSize: '0.9rem' }}>€</span>
                    </div>

                    <div style={{ minHeight: '52px' }}>
                        <p
                            className="mb-1 text-success"
                            style={{ fontSize: '0.8rem' }}
                        >
                            Consegna disponibile
                        </p>

                        {productInfo.stock > 10 ? (
                            <p
                                className="mb-0"
                                style={{
                                    fontWeight: '500',
                                    fontSize: '0.8rem'
                                }}
                            >
                                Disponibilità immediata
                            </p>
                        ) : (
                            <p
                                className="mb-0 text-danger"
                                style={{
                                    fontWeight: '500',
                                    fontSize: '0.8rem'
                                }}
                            >
                                Disponibilità limitata {<span style={{ fontWeight: 'bold'}}>
                                    {"< " + (productInfo.stock - 1)}
                                </span>}
                            </p>
                        )}
                    </div>

                    <div className="mt-auto d-flex justify-content-center">
                        <Button
                            variant="light"
                            onClick={() => setShowModal(true)}
                            style={{
                                borderRadius: '999px',
                                padding: '6px 14px',
                                border: '1px solid #bfbfbf',
                                width: '100%',
                                fontSize: '0.85rem'
                            }}
                        >
                            Recensioni
                        </Button>
                    </div>

                </Card.Body>
            </Card>

            <Review
                show={showModal}
                handleClose={() => setShowModal(false)}
                productInfo={productInfo}
            />
        </>
    );
}

export default Product;