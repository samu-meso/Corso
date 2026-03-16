export default function Post({ props }) {
    return (
        <section>
            <h2>{props.title}</h2>
            <p>Id: {props.id}</p>
            <p>{props.body}</p>
        </section>
    )
}