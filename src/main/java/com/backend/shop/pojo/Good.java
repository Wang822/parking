package com.backend.shop.pojo;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Good {
    @TableField("good_id")
    private int good_id;

    @TableField("name")
    private String name;

    @TableField("status")
    private int status;

    @TableField("price")
    private String price;

    @TableField("description")
    private String description;

    @TableField("campus")
    private int campus;

    @TableField("old")
    private int old;

    @TableField("seller_id")
    private int seller_id;

    @TableField("pic_addr")
    private String pic_addr;

    @TableField("deposit")
    private String deposit;

    @TableField("tag")
    private int tag;

    @TableField("publish_date")
    private Date publish_date;

    public int getGood_id() {
        return good_id;
    }

    public void setGood_id(int q_good_id) {
        this.good_id = q_good_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int q_status) {
        this.status = q_status;
    }

    public String getName() {
        return name;
    }

    public void setName(String q_name) {
        this.name = q_name;
    }

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

    public int getTag() {return tag;}

    public void setTag(int q_tag){this.tag=q_tag;}

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
