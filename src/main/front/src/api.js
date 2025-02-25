import axios from 'axios';

const api = axios.create({
    baseURL: 'http://localhost:8888/api',
    withCredentials: false, //  쿠키 사용 안 함 (헤더로 인증)
});

//  요청 인터셉터 추가 (JWT를 Authorization 헤더에 추가)
api.interceptors.request.use((config) => {
    const token = localStorage.getItem('token'); //  로컬 스토리지에서 JWT 가져오기
    if (token) {
        config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
});

export default api;
