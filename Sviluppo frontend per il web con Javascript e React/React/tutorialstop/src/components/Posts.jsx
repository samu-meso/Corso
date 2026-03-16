import axios from "axios";
import Post from "./Post";
import { useEffect, useState } from "react";

function Posts() {
    console.log("POSTS()")
    const [refresh, setRefresh] = useState(0);
    const [n, setN] = useState(0);
    const [posts, setPosts] = useState([]);

    const [loading, setLoading] = useState(true);
    const [err, setErr] = useState(null)

    useEffect(() => {
        const controller = new AbortController()

        async function fetchPosts() {
            try {
                setLoading(true);
                setErr(null);

                const response = await axios.get(
                    "https://dummyjson.com/posts?limit=3",
                    { signal: controller.signal });

                setPosts(response.data.posts)
            }
            catch (err) {
                console.dir(err);
                setErr(err)
            } finally {
                setLoading(false)
            }
        }

        fetchPosts();

        return () => {
            controller.abort()
            console.log("Component unmounted")
        }
    }, [refresh])

    function changeRefresh() {
        setRefresh(refresh + 1)
    }

    function changeN() {
        setN(n + 1)
    }

    if (err)
        return <p>{err.message}</p>
    return (
        <section>
            <h1>Posts!</h1>
            <button onClick={changeN}>N</button>
            <p>{n}</p>
            <button onClick={changeRefresh}>Refresh</button>
            <p>{refresh}</p>

            {/* {
                err && <p>{err.message}</p>
            }
            {
                loading && <p>loading...</p>
            } */}
            {
                posts.length > 0 ?
                    posts.map(post => (
                        <Post key={post.id} props={post} />
                    ))
                    : loading ? <p>Loading..</p> : <p>Nessun post disponibile</p>
            }
        </section>
    )
}

export default Posts;