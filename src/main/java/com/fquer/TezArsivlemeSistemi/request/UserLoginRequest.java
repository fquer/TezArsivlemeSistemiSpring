package com.fquer.TezArsivlemeSistemi.request;

import lombok.Data;

@Data
public class UserLoginRequest {
    private String userMail;
    private String userPassword;
}
