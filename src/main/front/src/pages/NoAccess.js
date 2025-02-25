import { Link } from "react-router-dom";

function NoAccess() {
    return (
        <div>
            <h1>🚫 접근 불가</h1>
            <p>이 페이지에 접근할 수 있는 권한이 없습니다.</p>
            <Link to="/">홈으로 돌아가기</Link>
        </div>
    );
}

export default NoAccess;
