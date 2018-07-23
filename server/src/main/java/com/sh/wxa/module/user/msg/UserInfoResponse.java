package com.sh.wxa.module.user.msg;

import com.sh.wxa.JsonMessage;
import lombok.Data;

@Data
public class UserInfoResponse extends JsonMessage {
    /**
     * 用户唯一id
     */
    private String openId;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 用户头像url
     */
    private String iconUrl;
}
