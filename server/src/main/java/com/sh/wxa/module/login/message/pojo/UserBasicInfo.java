package com.sh.wxa.module.login.message.pojo;

import com.sh.wxa.Message;
import lombok.Data;

@Data
public class UserBasicInfo implements Message {

    /**
     *  用户唯一ID
     */
    private String openId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 性别
     */
    private String sex;

    /**
     * 城市
     */
    private String city;
}
