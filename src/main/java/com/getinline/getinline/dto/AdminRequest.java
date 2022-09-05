package com.getinline.getinline.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

public class AdminRequest {

    private String email;
    private String nickname;
    private String password;
    private String phoneNumber;
    private String memo;

    public AdminRequest(String email, String nickname, String password, String phoneNumber, String memo) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.memo = memo;
    }


    public static AdminRequest of(
            String email,
            String nickname,
            String password,
            String phoneNumber,
            String memo
    ) {
        return new AdminRequest(email, nickname, password, phoneNumber, memo);
    }
}
