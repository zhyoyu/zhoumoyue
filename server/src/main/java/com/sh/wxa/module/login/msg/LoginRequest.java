package com.sh.wxa.module.login.msg;

import com.sh.wxa.Message;
import lombok.Data;

@Data
public class LoginRequest implements Message {

    private String openId;

    private String userName;

    private int sex;

    private String imageUrl;

    private String city;

}
