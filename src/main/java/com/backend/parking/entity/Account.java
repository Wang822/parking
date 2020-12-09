package com.backend.parking.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author dm
 * @since 2020-12-09
 */
@Data
@TableName("account")
@EqualsAndHashCode(callSuper = false)
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    private String openId;

    private Integer authenticated;

    //@TableField("nick_name")
    private String nickName;

    @TableField("create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh-mm-ss")
    private Date createTime;

    @TableField("last_visit_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh-mm-ss")
    private Date lastVisitTime;

    private String skey;

    //@TableField("session_key")
    private String sessionKey;


}
