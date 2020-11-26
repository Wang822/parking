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
    @Query(value = "update user set username = :username where userid = :userid",nativeQuery = true)
    void updateNameById(@Param("userid") Integer userid, @Param("username") String username);

}
