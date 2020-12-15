package com.backend.shop.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user")
public class User {
    private static final long serialVersionUID = 1L;

    @TableId(value = "user_id", type = IdType.INPUT)
    private int userId;

    @TableField("nick_name")
    private String nickName;

    @TableField("real_name")
    private String realName;

    @TableField("campus")
    private int campus;

    @TableField("college")
    private String college;

    @TableField("major")
    private String major;

    @TableField("grade")
    private int grade;
}
