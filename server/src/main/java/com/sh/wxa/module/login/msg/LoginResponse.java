package com.sh.wxa.module.login.msg;

import com.sh.wxa.JsonMessage;
import lombok.Data;

@Data
public class LoginResponse extends JsonMessage {

    private String userName;

    private String city;

    private String memo;

}
