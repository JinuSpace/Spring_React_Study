import React, { useEffect, useState } from "react";
import { useParams, Link } from "react-router-dom";
import axios from "axios";

function BoardDetail() {
    const { id } = useParams();
    const [post, setPost] = useState(null);

    useEffect(() => {
        fetchPost();
    }, []);

    const fetchPost = async () => {
        try {
            const response = await axios.get(`http://localhost:8888/api/posts/detail/${id}`);
            setPost(response.data);
        } catch (error) {
            console.error("게시글 불러오기 오류:", error);
        }
    };

    if (!post) {
        return <p>게시글을 불러오는 중...</p>;
    }

    return (
        <div>
            <h1>{post.title}</h1>
            <p>{post.content}</p>
            <Link to="/">목록으로 돌아가기</Link>
        </div>
    );
}

export default BoardDetail;
