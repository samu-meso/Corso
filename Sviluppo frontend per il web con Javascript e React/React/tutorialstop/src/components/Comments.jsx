function Comments({ commentsP }) {
    return (
        <ul>
            {
                commentsP.map((comment, index) => (
                    <li key={index}>{comment.tutorialTitle + " " + comment.text}</li>
                ))
            }   
        </ul>
    )
}

export default Comments;