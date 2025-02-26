import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

function BoardCreate() {
    const [title, setTitle] = useState("");
    const [content, setContent] = useState("");
    const navigate = useNavigate();

    const addPost = async () => {
        if (!title || !content) {
            alert("제목과 내용을 입력하세요.");
            return;
        }

        try {
            await axios.post("http://localhost:8000/posts", { title, content });
            navigate("/"); // 작성 후 게시판 리스트로 이동
        } catch (error) {
            console.error("게시글 작성 오류:", error);
        }
    };

    return (
        <div>
            <h1>게시글 작성</h1>
            <input
                type="text"
                placeholder="제목"
                value={title}
                onChange={(e) => setTitle(e.target.value)}
            />
            <textarea
                placeholder="내용"
                value={content}
                onChange={(e) => setContent(e.target.value)}
            />
            <button onClick={addPost}>작성 완료</button>
        </div>
    );
}

export default BoardCreate;
