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
public class AskForGoodPost {

    @TableId(value = "afg_post_id", type = IdType.AUTO)
    @ApiModelProperty(value = "AskForGoodPostId", example = "1")
    private int afgPostId;

    @TableField("user_id")
    @ApiModelProperty(value = "userId", example = "1")
    private int userId;

    @TableField("afg_intro")
    @ApiModelProperty(value = "AskForGoodPostIntro", example = "introduction")
    private String afgIntro;

    @TableField("afg_title")
    @ApiModelProperty(value = "AskForGoodPostTitle", example = "title")
    private String afgTitle;

    @TableField("afg_tag")
    @ApiModelProperty(value = "AskForGoodPostTag", example = "1")
    private int afgTag;

    @TableField("campus")
    @ApiModelProperty(value = "campus", example = "1")
    private int campus;

    @TableField("afg_price")
    @ApiModelProperty(value = "AskForGoodPostPrice", example = "1.0")
    private double afgPrice;

    @TableField("afg_condition")
    @ApiModelProperty(value = "AskForGoodPostCondition", example = "condition")
    private String afgCondition;

    @TableField("time")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @ApiModelProperty(value = "time", example = "2020-12-19 18:28:00")
    private Date time;

    private String nick_name=null;


}
