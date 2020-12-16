package com.backend.shop.pojo;
import java.util.Date;

public class Good {
    private int good_id;
    private String name;
    private int status;
    private String price;
    private String description;
    private int campus;
    private int old;
    private int seller_id;
    private String pic_addr;
    private String deposit;
    private String tag;
    private Date publish_date;

    public Good(){}
    public Good(int q_good_id, String q_name, int q_status, String q_price, String q_description, int q_campus, int q_old,
            int q_seller_id, String q_pic_addr, String q_deposit, String q_tag, Date q_publish_date)
    {
        this.good_id=q_good_id;
        this.name=q_name;
        this.status=q_status;
        this.price=q_price;
        this.description= q_description;
        this.campus= q_campus;
        this.old= q_old;
        this.seller_id= q_seller_id;
        this.pic_addr= q_pic_addr;
        this.deposit= q_deposit;
        this.tag= q_tag;
        this.publish_date= q_publish_date;
    }

    public int getGood_id(){return good_id;}
    public void setGood_id(int q_good_id){this.good_id=q_good_id;}

    public int getStatus(){return status;}
    public void setStatus(int q_status){this.status=q_status;}

    public String getName(){return name;}
    public void setName(String q_name){this.name=q_name;}

    public String getPrice() {return price;}
    public void setPrice(String q_price) {this.price=q_price;}

    public String getDescription() {return description;}
    public void setDescription(String q_description) {this.description=q_description;}

    public int getCampus() {return campus;}
    public void setCampus(int q_campus){this.campus=q_campus;}

    public int getOld() {return old;}
    public void setOld(int q_old){this.old=q_old;}

    public int getSeller_id(){return seller_id;}
    public void setSeller_id(int q_seller_id) {this.seller_id=q_seller_id;}

    public String getPic_addr(){return pic_addr;}
    public void setPic_addr(String q_pic_addr){this.pic_addr=q_pic_addr;}

    public String getDeposit() {return deposit;}
    public void setDeposit(String q_deposit) {this.deposit=q_deposit;}

    public String getTag() {return tag;}
    public void setTag(String q_tag){this.tag=q_tag;}

    public Date getPublish_date(){return publish_date;}
    public void setPublish_date(Date q_publish_date){this.publish_date=q_publish_date;}

    @Override
    public String toString() {
        return "Good{" +
                "good_id=" + good_id +
                ", name=" + name+
                ", status='" + status +
                ", price='" + price +
                ", description=" + description +
                ", campus=" + campus+
                ", old=" + old +
                ", seller_id=" + seller_id +
                ", pic_addr='" + pic_addr +
                ", deposit=" + deposit +
                ", tag" + tag+
                ", publish_date=" + publish_date +
                '}';
    }
}
