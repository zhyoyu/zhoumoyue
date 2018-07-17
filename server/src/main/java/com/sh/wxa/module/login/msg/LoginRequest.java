package com.sh.wxa.module.login.msg;

import com.sh.wxa.Message;
import lombok.Data;

@Data
public class LoginRequest implements Message {

    private String openId;

    private String nickName;

    private int sex;

    private String iconUrl;

    private String city;

}
