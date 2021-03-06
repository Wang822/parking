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
public class QuestionPostReply {

    @TableId(value = "q_reply_id", type = IdType.AUTO)
    @ApiModelProperty(value = "QuestionPostReplyId", example = "1")
    private int qReplyId;

    @TableField("user_id")
    @ApiModelProperty(value = "userId", example = "1")
    private int userId;

    @TableId("q_post_id")
    @ApiModelProperty(value = "QuestionPostId", example = "1")
    private int qPostId;

    @TableField("content")
    @ApiModelProperty(value = "QuestionPostReplyContent", example = "content")
    private String content;

    @TableField("time")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @ApiModelProperty(value = "time", example = "2020-12-19 18:28:00")
    private Date time;

    private String nick_name=null;

}
