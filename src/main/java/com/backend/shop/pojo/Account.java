package com.backend.shop.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@TableName("account")
public class Account {
    private static final long serialVersionUID = 1L;

    @TableId(value = "account_id", type = IdType.AUTO)
    private int accountId;

    @TableField("open_id")
    private String openId;

    @TableField("authenticated")
    private boolean authenticated;

    @TableField("nick_name")
    private String nickName;

    @TableField("create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date createTime;

    @TableField("last_visit_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date lastVisitTime;

//    @TableField("skey")
//    private String skey;
//
//    @TableField("session_key")
//    private String sessionKey;

}
