package com.example.demo.entity;

import javax.persistence.*;
//import jdk.nashorn.internal.objects.annotations.Getter;
//import jdk.nashorn.internal.objects.annotations.Setter;

@Entity
@Table(name = "user")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer user_id;

    public String wx_id;

    public String password;

    public String avatar;

    public String phone_num;

    public String real_name;

    public String id_card_no;

    public Integer role;


    public Integer getUserId() {
        return user_id;
    }

    public String getWxId() {
        return wx_id;
    }

    public String getPassword() { return password; }

    public String getAvatar() {
        return avatar;
    }

    public String getPhoneNum() {
        return phone_num;
    }

    public String getRealName() {
        return real_name;
    }

    public String getIdCardNo() {
        return id_card_no;
    }

    public Integer getRole() {
        return role;
    }


    public void setUserId(Integer userid){
        this.user_id=userid;
    }
    public void setWxId(String wx_id){
        this.wx_id=wx_id;
    }

    public void setPassword(String password){
        this.password=password;
    }

    public void setAvatar(String avatar){
        this.avatar=avatar;
    }

    public void setPhoneNum(String phone_num){
        this.phone_num=phone_num;
    }

    public void setRealName(String real_name){
        this.real_name=real_name;
    }

    public void setIdCardNo(String id_card_no){
        this.id_card_no=id_card_no;
    }

    public void setRole(Integer role){
        this.role=role;
    }



}
