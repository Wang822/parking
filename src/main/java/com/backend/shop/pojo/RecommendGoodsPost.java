package com.backend.shop.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RecommendGoodsPost {

    @TableId(value = "rg_post_id", type = IdType.AUTO)
    private int rgPostId;

    @TableField("user_id")
    private int userId;

    @TableField("rg_intro")
    private String rgIntro;

    @TableField("rg_title")
    private String rgTitle;

    @TableField("rg_tag")
    private int rgTag;

    @TableField("time")
    private Date time;
}
