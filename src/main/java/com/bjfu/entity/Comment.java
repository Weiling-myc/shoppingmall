package com.bjfu.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * 评论实体类
 */
@Entity
@Table(name = "`comment`")
public class Comment implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column
    private Integer id;
    @Column
    private Integer orderId;
    @Column
    private String content;
    @Column
    private Date createTime;


    public Comment(Integer id, Integer orderId, String content, Date createTime) {
        super();
        this.id = id;
        this.orderId = orderId;
        this.content = content;
        this.createTime = createTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
