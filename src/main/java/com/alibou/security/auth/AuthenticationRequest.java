package com.alibou.security.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder //建造者模式，並生成一個內部靜態 Builder 類。
@AllArgsConstructor //包含所有屬性的有參數建構子
@NoArgsConstructor //無參數建構子
public class AuthenticationRequest {

    private String email;
    private String password;
}
