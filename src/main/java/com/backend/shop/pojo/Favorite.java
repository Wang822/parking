package com.backend.shop.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Favorite {

    @TableField("user_id")
    @ApiModelProperty(value = "user's id", example = "6")
    private int userId;

    @TableField("good_id")
    @ApiModelProperty(value = "good's id", example = "10")
    private int goodId;
}
