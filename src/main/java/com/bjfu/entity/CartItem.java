package com.bjfu.entity;
import javax.persistence.*;
import java.io.Serializable;
/**
 * TODO
 *
 * @author 123
 * @version 1.0
 * @date 2021/1/1 20:22
 */
public class CartItem implements Serializable {

    @Id
    @GeneratedValue
    @Column
    private Integer id;
    /**
     * 商品Id
     */
    @Column
    private Integer productId;
    /**
     * 数量
     */
    @Column
    private Integer count;
    /**
     * 总价
     */
    public CartItem(Integer productId)
    {
        this.productId=productId;
    }

    public CartItem(Integer productId,Integer count)
    {
        this.productId=productId;
        this.count=count;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
