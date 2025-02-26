import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Header from './components/Header';
import Login from './pages/Login';
import BoardList from './pages/BoardList';
import Admin from './pages/Admin';
import Home from './pages/Home'; // 기본 홈 페이지
import NoAccess from './pages/NoAccess';
import Auth from './pages/Auth';
import BoardPage from "./pages/BoardPage";
import PostDetail from "./pages/PostDetail"; // 자유게시판 & 공지사항 공통 페이지


function App() {
    return (
        <Router>
            <div className="App">
                <Header />
                <Routes>
                    <Route path="/" element={<Home />} />
                    <Route path="/home" element={<Home />} />
                    <Route path="/login" element={<Login />} />
                    <Route path="/board" element={<BoardList />} /> {/* 게시판 목록 */}
                    <Route path="/no-access" element={<NoAccess />} />

                    {/* 자유게시판 & 공지사항 공통 라우트 */}
                    <Route path="/board/:boardName" element={<BoardPage />} />

                    <Route path="/post/:postId" element={<PostDetail />} />

                    {/* 관리자 페이지 (ADMIN 권한 필요) */}
                    <Route element={<Auth allowedRoles={["ADMIN"]} />}>
                        <Route path="/admin" element={<Admin />} />
                    </Route>
                </Routes>
            </div>
        </Router>
    );
}

export default App;