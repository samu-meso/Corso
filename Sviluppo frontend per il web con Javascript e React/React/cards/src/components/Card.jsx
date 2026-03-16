function Card(props) {
    // Commento uno {sottotitolo: "Aaa"}
    /* Commento due*/
    return (
        <article>
            <h2>{props.sottotitolo}</h2>
            <img src="https://images.pexels.com/photos/21554935/pexels-photo-21554935/free-photo-of-paesaggio-tramonto-moda-uomo.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1" 
                widht="200px" 
                height="200px"
                className='avatar' 
            />
            <p>{props.paragrafoUno}</p>
            <p>{props.paragrafoDue}</p>
        </article>
    )
}

export default Card;