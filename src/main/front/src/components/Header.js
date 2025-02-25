import { Link } from 'react-router-dom';

function Header() {
    return (
        <nav>
            <ul>
                <li><Link to="/">홈</Link></li>
                <li><Link to="/login">로그인</Link></li>
                <li><Link to="/board">게시판</Link></li>
                <li><Link to="/admin">관리자</Link></li>
            </ul>
        </nav>
    );
}

export default Header;
