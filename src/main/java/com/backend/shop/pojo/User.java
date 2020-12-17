package com.backend.shop.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("user")
@ApiModel("用户个人信息")
public class User {
    private static final long serialVersionUID = 1L;

    @TableId(value = "user_id", type = IdType.INPUT)
    @ApiModelProperty("ID")
    private int userId;

    @TableField("nick_name")
    @ApiModelProperty("昵称")
    private String nickName;

    @TableField("real_name")
    @ApiModelProperty("真实姓名")
    private String realName;

    @TableField("campus")
    @ApiModelProperty("校区")
    private int campus;

    @TableField("college")
    @ApiModelProperty("学院")
    private String college;

    @TableField("major")
    @ApiModelProperty("专业")
    private String major;

    @TableField("grade")
    @ApiModelProperty("年级")
    private int grade;
}
