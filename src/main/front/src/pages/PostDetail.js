import React, { useEffect, useState } from "react";
import { useParams, Link } from "react-router-dom";
import axios from "axios";

function PostDetail() {
    const { postId } = useParams(); // URL에서 postId 가져오기
    const [post, setPost] = useState(null);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        axios.get(`http://localhost:8888/api/posts/${postId}`)
            .then(response => {
                console.log("response ->", response);
                setPost(response.data);
                setLoading(false);
            })
            .catch(error => {
                console.error("게시글을 불러오는 중 오류 발생:", error);
                setLoading(false);
            });
    }, [postId]);

    if (loading) return <p>게시글을 불러오는 중...</p>;

    return (
        <div>
            <h1>{post?.title}</h1>
            <p>{post?.content}</p>
            <p>작성자: {post?.author}</p>
        </div>
    );
}

export default PostDetail;
