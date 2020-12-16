package com.backend.shop.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
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
    private String content;

    @TableField("sender_id")
    private int senderId;

    @TableField("receiver_id")
    private int receiverId;

    @TableField("time")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date time;
}
