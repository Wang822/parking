package com.backend.shop.pojo;
import java.sql.Timestamp;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Good {
    @TableId(value="gid", type= IdType.INPUT)
    @ApiModelProperty(value = "商品id", example = "1234")
    private int gid;

    @TableField("name")
    @ApiModelProperty(value = "商品名", example = "2")
    private String name;

    @TableField("price")
    @ApiModelProperty(value = "价格", example = "12.6")
    private double price;

    @TableField("description")
    @ApiModelProperty(value = "商品描述", example = "description")
    private String description;

    @TableField("campus")
    @ApiModelProperty(value = "校区", example = "2")
    private int campus;

    @TableField("old")
    @ApiModelProperty(value = "成色，9-9成旧", example = "9")
    private int old;

    @TableField("seller")
    @ApiModelProperty(value = "卖家ID", example = "1224")
    private int seller;

    @TableField("pic")
    @ApiModelProperty(value = "图片地址", example = "pic_addr")
    private String pic;

    @TableField("tag")
    @ApiModelProperty(value = "标签", example = "4")
    private int tag;

    @TableField("publish")
    @ApiModelProperty(value = "发布时间", example = "2020-12-10 23:13:10")
    private Timestamp publish;

    public int getGid() {
        return gid;
    }

    public void setGid(int q_good_id) {
        this.gid = q_good_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String q_name) {
        this.name = q_name;
    }

    public double getPrice() {return price;}

    public void setPrice(double q_price) {this.price=q_price;}

    public String getDescription() {return description;}

    public void setDescription(String q_description) {this.description=q_description;}

    public int getCampus() {return campus;}

    public void setCampus(int q_campus){this.campus=q_campus;}

    public int getOld() {return old;}

    public void setOld(int q_old){this.old=q_old;}

    public int getSeller(){return seller;}

    public void setSeller(int q_seller_id) {this.seller=q_seller_id;}

    public String getPic(){return pic;}

    public void setPic(String q_pic_addr){this.pic=q_pic_addr;}

    public int getTag() {return tag;}

    public void setTag(int q_tag){this.tag=q_tag;}

    public Timestamp getPublish(){return publish;}

    public void setPublish(Timestamp q_publish_date){this.publish=q_publish_date;}

    @Override
    public String toString() {
        return "Good{" +
                "goodId=" + gid +
                ", name=" + name+
                ", price='" + price +
                ", description=" + description +
                ", campus=" + campus+
                ", old=" + old +
                ", sellerId=" + seller +
                ", picAddr='" + pic +
                ", tag" + tag+
                ", publishDate=" + publish +
                '}';
    }
}
