package com.backend.parking.mapper;

import com.backend.parking.entity.Account;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author dm
 * @since 2020-12-09
 */
@Mapper
//@Component(value = "accountMapper")
public interface AccountMapper extends BaseMapper<Account> {
    @Select("SELECT * FROM 'account' WHERE open_id = #{openId}")
    //@Result(property = "user_id", column = "user_id")
    Account getAccountByOpenId(String openId);
}
