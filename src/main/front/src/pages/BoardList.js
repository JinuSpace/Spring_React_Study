import React from "react";
import { Link } from "react-router-dom";

function BoardList() {
    const boards = [
        { name: "자유게시판", path: "/board/free" },
        { name: "공지사항", path: "/board/notice" },
    ];

    return (
        <div>
            <h1>게시판 목록</h1>
            <ul>
                {boards.map((board) => (
                    <li key={board.path}>
                        <Link to={board.path}>{board.name}</Link>
                    </li>
                ))}
            </ul>
        </div>
    );
}

export default BoardList;
