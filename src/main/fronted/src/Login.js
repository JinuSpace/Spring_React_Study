import React, { useState } from 'react';
import axios from 'axios';

function Login() {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [errorMessage, setErrorMessage] = useState('');

    const handleLogin = (e) => {
        e.preventDefault();

        const loginData = {
            email: email,
            password: password,
        };

        // 서버에 로그인 요청 보내기 (예: POST 요청)
        axios.post('/api/login', loginData)
            .then((response) => {
                // 로그인 성공 처리 (토큰 저장 등)
                console.log('로그인 성공:', response.data);
                // 성공 시, 다른 페이지로 리다이렉트하거나 상태 변경
            })
            .catch((error) => {
                // 로그인 실패 처리
                setErrorMessage('로그인에 실패했습니다. 이메일과 비밀번호를 확인해주세요.');
            });
    };

    return (
        <div style={{ maxWidth: '400px', margin: '0 auto' }}>
            <h2>로그인</h2>
            <form onSubmit={handleLogin}>
                <div>
                    <label htmlFor="email">이메일</label>
                    <input
                        type="email"
                        id="email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        required
                    />
                </div>
                <div>
                    <label htmlFor="password">비밀번호</label>
                    <input
                        type="password"
                        id="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        required
                    />
                </div>
                {errorMessage && <p style={{ color: 'red' }}>{errorMessage}</p>}
                <button type="submit">로그인</button>
            </form>
        </div>
    );
}

export default Login;
