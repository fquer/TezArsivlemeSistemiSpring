package com.fquer.TezArsivlemeSistemi.dto;

import com.fquer.TezArsivlemeSistemi.model.User;
import lombok.Data;

@Data
public class UserDto {
    private String id;
    private String userName;
    private String userSurname;
    private String userMail;

    public UserDto(User user) {
        this.id = user.getId();
        this.userName = user.getUserName();
        this.userSurname = user.getUserSurname();
        this.userMail = user.getUserMail();
    }
}
