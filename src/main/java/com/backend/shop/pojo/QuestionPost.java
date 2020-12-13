package com.backend.shop.pojo;


import java.sql.Date;

public class QuestionPost {

    private int q_post_id;
    private int user_id;
    private String q_content;
    private String q_title;
    private Date time;

    public QuestionPost() {
    }

    public QuestionPost(int q_post_id, int user_id, String q_content, String q_title, Date time) {
        this.q_post_id = q_post_id;
        this.user_id = user_id;
        this.q_content = q_content;
        this.q_title = q_title;
        this.time = time;
    }

    public int getQ_post_id() {
        return q_post_id;
    }

    public void setQ_post_id(int q_post_id) {
        this.q_post_id = q_post_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getQ_content() {
        return q_content;
    }

    public void setQ_content(String q_content) {
        this.q_content = q_content;
    }

    public String getQ_title() {
        return q_title;
    }

    public void setQ_title(String q_title) {
        this.q_title = q_title;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "QuestionPost{" +
                "q_post_id=" + q_post_id +
                ", user_id=" + user_id +
                ", q_content='" + q_content + '\'' +
                ", q_title='" + q_title + '\'' +
                ", time=" + time +
                '}';
    }
}
