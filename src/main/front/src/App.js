import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Header from './components/Header';
import Login from './pages/Login';
import Board from './pages/Board';
import Admin from './pages/Admin';
import Home from './pages/Home'; // 기본 홈 페이지
import NoAccess from './pages/NoAccess';
import Auth from './pages/Auth';

function App() {
    return (
        <Router>
            <div className="App">
                <Header />
                <Routes>
                    <Route path="/" element={<Home />} />  {/* 기본 홈 페이지 */}
                    <Route path="/home" element={<Home />} />
                    <Route path="/login" element={<Login />} />
                    <Route path="/board" element={<Board />} />
                    <Route path="/no-access" element={<NoAccess />} />

                    <Route element={<Auth allowedRoles={["ADMIN"]} />}>
                        <Route path="/admin" element={<Admin />} />
                    </Route>
                </Routes>
            </div>
        </Router>
    );
}

export default App;
