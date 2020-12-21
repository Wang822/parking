package com.backend.shop.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Chat {
    @TableField("content")
    @ApiModelProperty(value = "message's content", example = "哈喽，早上好")
    private String content;

    @TableField("sender_id")
    @ApiModelProperty(value = "sender's id", example = "6")
    private int senderId;

    @TableField("receiver_id")
    @ApiModelProperty(value = "receiver's id", example = "1")
    private int receiverId;

    @TableField("time")
    @ApiModelProperty(value = "message sending time", example = "2020-12-19 23:22:11")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date time;
}
