package com.sh.wxa.module.login.message;

import com.sh.wxa.Message;
import lombok.Data;

@Data
public class LoginRequest implements Message {

    private String memo;

}
