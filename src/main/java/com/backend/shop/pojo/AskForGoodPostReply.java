package com.backend.shop.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AskForGoodPostReply {


        @TableId(value = "afg_reply_id", type = IdType.AUTO)
        @ApiModelProperty(value = "AskForGoodPostReplyId", example = "1")
        private int afgReplyId;

        @TableField("user_id")
        @ApiModelProperty(value = "userId", example = "1")
        private int userId;

        @TableId("afg_post_id")
        @ApiModelProperty(value = "AskForGoodPostId", example = "1")
        private int afgPostId;

        @TableField("content")
        @ApiModelProperty(value = "AskForGoodPostReplyContent", example = "content")
        private String content;

        @TableField("time")
        @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
        @ApiModelProperty(value = "time", example = "2020-12-19 18:28:00")
        private Date time;

        private String nick_name=null;



}
