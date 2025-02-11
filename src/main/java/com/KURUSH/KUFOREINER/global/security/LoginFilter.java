package com.KURUSH.KUFOREINER.global.security;

import com.KURUSH.KUFOREINER.global.LoginRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class LoginFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    private final JWTUtil jwtUtil;

    private final ObjectMapper objectMapper = new ObjectMapper();



    public LoginFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {

        this.authenticationManager = authenticationManager;
        this.jwtUtil=jwtUtil;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        System.out.println(request.toString());
        logger.info(request.toString());

        if (!request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)) {
            // Content-Type이 "application/x-www-form-urlencoded"인 경우

            String username = obtainUsername(request);
            String password = obtainPassword(request);
            logger.info("추출한 username : "+username);
            logger.info("추출한 비밀번호 : "+password);

            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password, null);

            return authenticationManager.authenticate(authToken);
        }

        try {
            // Content-Type이 "application/json"일 경우
            LoginRequest loginRequest = objectMapper.readValue(request.getInputStream(), LoginRequest.class);


            String username = loginRequest.userId();
            String password = loginRequest.password();
            logger.info("추출한 username : "+username);
            logger.info("추출한 비밀번호 : " + password);

            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password, null);

            return authenticationManager.authenticate(authToken);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication)
            throws IOException {

        //UserDetailsS
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();

        String username = customUserDetails.getUsername();


        String password = customUserDetails.getPassword();
        //AT : 6분
        String accessToken = jwtUtil.createJwt(username, password, 60*60*10000L);
        //RT : 7일
        String refreshToken = jwtUtil.createRefreshToken(username, password, 86400000*7L);

        sendTokenResponse(response,accessToken,refreshToken);
        Cookie cookie = createCookie(refreshToken);
        response.addCookie(cookie);


    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed)
            throws IOException {

        setResponse(response, 406,"아이디나 비밀번호가 일치하지 않습니다.");
    }
    public Cookie createCookie(String refreshToken) throws UnsupportedEncodingException {
        String cookieName = "refreshtoken";
        String cookieValue = refreshToken;
        Cookie cookie = new Cookie(cookieName, cookieValue);

        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60 * 24 * 7);

        return cookie;
    }
    private void setResponse(HttpServletResponse response,int status, String message) throws RuntimeException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(status);
        response.getWriter().print(message);
    }
    private void sendTokenResponse(HttpServletResponse response, String AT,String RT ) throws IOException {
        String jsonResponse = "{\"accessToken\": \"" +"Bearer " + AT +
                "\", \"refreshToken\": \"" +"Bearer "+ RT + "\"}";

        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpStatus.OK.value());
        response.getWriter().print(jsonResponse);
    }
}
