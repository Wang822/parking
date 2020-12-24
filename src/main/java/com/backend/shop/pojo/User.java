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
@ApiModel(value = "User", description = "User Information")
public class User {
    private static final long serialVersionUID = 1L;

    @TableId(value = "user_id", type = IdType.INPUT)
    @ApiModelProperty(value = "ID", example = "2")
    private int userId;

    @TableField("nick_name")
    @ApiModelProperty(value = "昵称", example = "nick")
    private String nickName;

    @TableField("real_name")
    @ApiModelProperty(value = "真实姓名", example = "real")
    private String realName;

    @TableField("campus")
    @ApiModelProperty(value = "校区",example = "1")
    private int campus;

    @TableField("college")
    @ApiModelProperty(value = "学院", example = "sse")
    private String college;

    @TableField("major")
    @ApiModelProperty(value = "专业", example = "se")
    private String major;

    @TableField("grade")
    @ApiModelProperty(value = "年级", example = "3")
    private int grade;

    @TableField("avatar")
    @ApiModelProperty(value = "头像图片路径(wx.cloud)", example = "example.png")
    private String avatar;
}
