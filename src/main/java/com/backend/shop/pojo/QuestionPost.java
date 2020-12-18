package com.backend.shop.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class QuestionPost {

    @TableId(value = "q_post_id", type = IdType.AUTO)
    private int qPostId;

    @TableField("user_id")
    private int userId;

    @TableField("q_content")
    private String qContent;

    @TableField("q_title")
    private String qTitle;

    @TableField("time")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date time;

    private String nick_name=null;

}

