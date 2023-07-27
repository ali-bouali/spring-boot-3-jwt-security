package com.alibou.security.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor  //Lombok 自動生成帶有 final 屬性的建構式
public class AuthenticationController {

    private final AuthenticationService service;

    // 處理用戶註冊請求，接收並解析 RegisterRequest 物件作為請求主體，並返回 AuthenticationResponse 物件作為回應內容
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(service.register(request));
    }

    // 處理用戶身份驗證請求，接收並解析 AuthenticationRequest 物件作為請求主體，並返回 AuthenticationResponse 物件作為回應內容
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    // 處理更新 JWT Token 的請求，這是一個沒有回應內容的動作，只是透過 HttpServletResponse 將新的 Token 返回給客戶端
    @PostMapping("/refresh-token")
    public void refreshToken(HttpServletRequest request,
                             HttpServletResponse response) throws IOException {
        service.refreshToken(request, response);
    }


}
