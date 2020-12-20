package com.backend.shop.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReplyList {

    private int user_id;
    private String nick_name;
    private String avatar;
    //private String content;

}
