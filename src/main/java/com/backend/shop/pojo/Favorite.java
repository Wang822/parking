package com.backend.shop.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Favorite {

    @TableField("user_id")
    private int userId;

    @TableField("good_id")
    private int goodId;
}
