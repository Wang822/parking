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
public class RecommendGoodsPost {

    @TableId(value = "rg_post_id", type = IdType.AUTO)
    @ApiModelProperty(value = "RecommendGoodsPostId", example = "1")
    private int rgPostId;

    @TableField("user_id")
    @ApiModelProperty(value = "userId", example = "1")
    private int userId;

    @TableField("rg_intro")
    @ApiModelProperty(value = "RecommendGoodsPostIntro", example = "introduction")
    private String rgIntro;

    @TableField("rg_title")
    @ApiModelProperty(value = "RecommendGoodsPostTitle", example = "title")
    private String rgTitle;

    @TableField("rg_tag")
    @ApiModelProperty(value = "RecommendGoodsPostTag", example = "1")
    private int rgTag;

    @TableField("time")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @ApiModelProperty(value = "time", example = "2020-12-19 18:28:00")
    private Date time;

    private String nick_name=null;
}
