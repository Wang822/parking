package com.example.demo.entity;

import javax.persistence.*;
import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

@Entity
@Table(name = "user")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer userid;

    public String username;

    public String password;

    public float account;


    public Integer getUserId() {
        return userid;
    }

    public String getUserName() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public float getAccount() {
        return account;
    }


    public void setUserId(Integer userid){
        this.userid=userid;
    }

    public void setUserName(String username){
        this.username=username;
    }

    public void setPassword(String password){
        this.password=password;
    }

    public void setAccount(float account){
        this.account=account;
    }



}
