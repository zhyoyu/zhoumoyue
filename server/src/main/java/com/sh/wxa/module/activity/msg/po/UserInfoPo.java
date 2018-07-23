package com.sh.wxa.module.activity.msg.po;

import com.sh.wxa.Message;
import lombok.Data;

@Data
public class UserInfoPo implements Message {
    /**
     * 用户id
     */
    private String openId;
    /**
     * 用户昵称
     */
    private String userName;
    /**
     * 用户头像url
     */
    private String iconUrl;
}
