import React, { useEffect, useState } from "react";
import { useParams, Link } from "react-router-dom";
import axios from "axios";

function BoardPage() {
    console.log("Board page in");
    const { boardName } = useParams(); // URL에서 boardName 가져옴
    const [posts, setPosts] = useState([]);

    useEffect(() => {
        axios.get(`http://localhost:8888/api/posts/${boardName}`).then((response) => {
            console.log("get success");
            setPosts(response.data);
        });
    }, [boardName]);

    return (
        <div>
            <h1>{boardName === "free" ? "자유게시판" : "공지사항"}</h1>
            <ul>
                {posts.map((post) => (
                    <li key={post.id}>
                        <Link to={`/post/${post.id}`}>[{post.id}]{post.title}</Link>
                    </li>
                ))}
            </ul>
            <Link to="/board">← 게시판 목록</Link>
        </div>
    );
}

export default BoardPage;
