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
public class AskForGoodPost {

    @TableId(value = "afg_post_id", type = IdType.AUTO)
    private int afgPostId;

    @TableField("user_id")
    private int userId;

    @TableField("afg_intro")
    private String afgIntro;

    @TableField("afg_title")
    private String afgTitle;

    @TableField("afg_tag")
    private int afgTag;

    @TableField("campus")
    private int campus;

    @TableField("afg_price")
    private double afgPrice;

    @TableField("afg_condition")
    private String afgCondition;

    @TableField("time")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date time;

}
