package com.fquer.TezArsivlemeSistemi.request;

import lombok.Data;

@Data
public class UserRequest {
    private String userName;
    private String userSurname;
    private String userMail;
    private String userTypeId;
    private String userPassword;
}
