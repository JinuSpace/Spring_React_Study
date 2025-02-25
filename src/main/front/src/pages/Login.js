import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import api from "../api"; // Axios 인스턴스

function Login() {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [errorMessage, setErrorMessage] = useState("");
    const [loading, setLoading] = useState(false); // 로딩 상태 추가

    const navigate = useNavigate();

    const handleLogin = async (e) => {
        e.preventDefault();

        const loginData = {
            email,
            password,
        };

        try {
            setLoading(true); // 로딩 상태 시작
            setErrorMessage(""); // 에러 메시지 초기화

            const response = await api.post("/login", loginData);

            // ✅ 로그인 성공 처리 (JWT를 localStorage에 저장)
            const token = response.data; //  서버에서 받은 JWT
            localStorage.setItem("token", token); //  localStorage에 저장
            console.log("로그인 성공:", token);

            navigate("/"); //  로그인 후 홈 페이지로 이동

        } catch (error) {
            // 로그인 실패 처리
            if (error.response && error.response.data) {
                console.error("Login Error:", error.response.data);
                setErrorMessage(error.response.data.message || "로그인에 실패했습니다.");
            } else {
                setErrorMessage("로그인 중 오류가 발생했습니다.");
            }
        } finally {
            setLoading(false); // 로딩 상태 종료
        }
    };

    return (
        <div style={{ maxWidth: "400px", margin: "0 auto", padding: "20px", border: "1px solid #ccc", borderRadius: "10px" }}>
            <h2 style={{ textAlign: "center" }}>로그인</h2>
            <form onSubmit={handleLogin}>
                <div style={{ marginBottom: "15px" }}>
                    <label htmlFor="email" style={{ display: "block", marginBottom: "5px" }}>이메일</label>
                    <input
                        type="email"
                        id="email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        required
                        style={{ width: "100%", padding: "8px", boxSizing: "border-box" }}
                    />
                </div>
                <div style={{ marginBottom: "15px" }}>
                    <label htmlFor="password" style={{ display: "block", marginBottom: "5px" }}>비밀번호</label>
                    <input
                        type="password"
                        id="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        required
                        style={{ width: "100%", padding: "8px", boxSizing: "border-box" }}
                    />
                </div>
                {errorMessage && (
                    <p style={{ color: "red", marginBottom: "15px", textAlign: "center" }}>
                        {errorMessage}
                    </p>
                )}
                <button
                    type="submit"
                    disabled={!email || !password || loading} // 입력값 없거나 로딩 중일 때 비활성화
                    style={{
                        width: "100%",
                        padding: "10px",
                        backgroundColor: loading ? "#ccc" : "#007BFF",
                        color: "#fff",
                        border: "none",
                        borderRadius: "5px",
                        cursor: loading ? "not-allowed" : "pointer",
                    }}
                >
                    {loading ? "로그인 중..." : "로그인"}
                </button>
            </form>
        </div>
    );
}

export default Login;
