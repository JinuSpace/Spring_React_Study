import { Navigate, Outlet } from "react-router-dom";
import { useEffect, useState } from "react";

// ✅ Base64 URL 디코딩 함수 (JWT 디코딩용)
const base64UrlDecode = (base64) => {
    try {
        return decodeURIComponent(
            atob(base64.replace(/-/g, "+").replace(/_/g, "/"))
                .split("")
                .map((c) => "%" + ("00" + c.charCodeAt(0).toString(16)).slice(-2))
                .join("")
        );
    } catch (e) {
        console.error("JWT 디코딩 오류:", e);
        return null;
    }
};

// ✅ JWT에서 역할(Role) 추출 함수
const getRoleFromToken = (token) => {
    try {
        const payloadBase64 = token.split(".")[1]; // JWT의 payload 부분
        const payloadDecoded = JSON.parse(base64UrlDecode(payloadBase64));
        return payloadDecoded.role; // ✅ role 값 반환
    } catch (error) {
        console.error("토큰 파싱 오류:", error);
        return null;
    }
};

const Auth = ({ allowedRoles }) => {
    const [userRole, setUserRole] = useState(null);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const token = localStorage.getItem("token"); //  localStorage에서 JWT 가져오기
        if (!token) {
            console.error("No token found");
            setLoading(false);
            setUserRole(null);
            return;
        }

        const role = getRoleFromToken(token); //  JWT에서 역할(Role) 추출
        if (!role) {
            console.error("Invalid token");
            setLoading(false);
            setUserRole(null);
            return;
        }

        console.log("User Role:", role);
        setUserRole(role);
        setLoading(false);
    }, []);

    if (loading) return <p>Loading...</p>; //  로딩 중이면 표시

    if (!userRole) return <Navigate to="/login" replace />; //  로그인되지 않았으면 로그인 페이지로 이동

    if (!allowedRoles.includes(userRole)) return <Navigate to="/no-access" replace />; //  권한 없는 경우

    return <Outlet />;
};

export default Auth;
