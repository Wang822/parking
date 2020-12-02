package com.example.demo.dao;


import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserRepository extends JpaRepository<User,Integer> {

    @Modifying
    @Query(value = "update user set wx_id = :wx_id where user_id = :user_id",nativeQuery = true)
    void updateNameByUser_id(@Param("user_id") Integer user_id, @Param("wx_id") String wx_id);

}
