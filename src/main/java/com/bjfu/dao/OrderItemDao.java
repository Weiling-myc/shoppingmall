package com.bjfu.dao;

import com.bjfu.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 订单数据层
 */
public interface OrderItemDao extends JpaRepository<OrderItem, Integer> {
    /**
     * 根据订单Id查询
     *
     * @param orderId
     * @return
     */
    List<OrderItem> findByOrderId(int orderId);
}
