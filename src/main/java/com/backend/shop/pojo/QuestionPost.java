package com.backend.shop.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class QuestionPost {

    @TableId(value = "q_post_id", type = IdType.AUTO)
    @ApiModelProperty(value = "QuestionPostId", example = "1")
    private int qPostId;

    @TableField("user_id")
    @ApiModelProperty(value = "userId", example = "1")
    private int userId;

    @TableField("q_content")
    @ApiModelProperty(value = "QuestionPostContent", example = "content")
    private String qContent;

    @TableField("q_title")
    @ApiModelProperty(value = "QuestionPostTitle", example = "title")
    private String qTitle;

    @TableField("time")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @ApiModelProperty(value = "time", example = "2020-12-19 18:28:00")
    private Date time;

    private String nick_name=null;

}

