package com.sh.wxa.module.login.msg.po;

import com.sh.wxa.Message;
import lombok.Data;

@Data
public class UserBasicPo implements Message {

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
