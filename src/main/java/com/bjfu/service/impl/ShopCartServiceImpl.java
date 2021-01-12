package com.bjfu.service.impl;

import com.bjfu.entity.CartItem;
import com.bjfu.entity.OrderItem;
import com.bjfu.entity.Product;
import com.bjfu.entity.User;
import com.bjfu.service.ProductService;
import com.bjfu.service.ShopCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 *
 */
@Service
public class ShopCartServiceImpl implements ShopCartService {

    @Autowired
    private ProductService productService;

    /**
     * 加购物车
     * 将封装成CartItem类型的商品保存到Session中List<CartItem>中
     *
     * @param cartItem
     * @param request
     */
    @Override
    public void addCart(CartItem cartItem, HttpServletRequest request) throws Exception {
        User loginUser = (User) request.getSession().getAttribute("user");
        if (loginUser == null)
            throw new Exception("未登录！请重新登录");
        List<CartItem> cartItems = (List<CartItem>) request.getSession().getAttribute(NAME_PREFIX + loginUser.getId());
        if (cartItems == null) {
            cartItems = new ArrayList<>();
            request.getSession().setAttribute(NAME_PREFIX + loginUser.getId(), cartItems);
        }
        cartItems.add(cartItem);
        //更新NAME_PREFIX + loginUser.getId()
        request.getSession().setAttribute(NAME_PREFIX + loginUser.getId(), cartItems);
    }

    /**
     * 移除
     * <p>
     * 移除session List中对应CartItem类型的商品
     *
     * @param cartItem
     * @param request
     */
    @Override
    public void remove(CartItem cartItem, HttpServletRequest request) throws Exception {
        User loginUser = (User) request.getSession().getAttribute("user");
        if (loginUser == null)
            throw new Exception("未登录！请重新登录");

        List<CartItem> cartItems = (List<CartItem>) request.getSession().getAttribute(NAME_PREFIX + loginUser.getId());
        Iterator<CartItem> cartIterator = cartItems.iterator();
        while (cartIterator.hasNext()) {
            if (cartItem.getProductId() == cartIterator.next().getProductId()) {
                cartIterator.remove();
            }
        }
    }

    /**
     * 查看购物车
     * <p>
     * 查询出session的List中所有的商品,并封装成List<OrderItem>返回
     *
     * @param request
     * @return
     */
    @Override

    public List<OrderItem> listCart(HttpServletRequest request) throws Exception {
        User loginUser = (User) request.getSession().getAttribute("user");
        if (loginUser == null)
            throw new Exception("未登录！请重新登录");
        List<CartItem> cartItems = (List<CartItem>) request.getSession().getAttribute(NAME_PREFIX + loginUser.getId());
        // key: productId value:OrderItem 存储原购物车数据
        Map<Integer, OrderItem> productMap = new HashMap<>();
        if (cartItems == null) {
            return new ArrayList<>();
        }
        // 遍历List中的商品id，每个商品Id对应一个OrderItem
        for (CartItem cartItem : cartItems) {
            //由cartItem 封装的productId和count两个变量
            int productId = cartItem.getProductId();
            System.out.println("商品Id：" + productId);
            int productCount = cartItem.getCount();
            Product product = productService.findById(productId);
            //该商品在购物车中不存在
            if (productMap.get(productId) == null) {
                OrderItem orderItem = new OrderItem();
                orderItem.setProduct(product);
                orderItem.setProductId(productId);
                orderItem.setCount(productCount);
                orderItem.setSubTotal(product.getShopPrice() * productCount);
                productMap.put(productId, orderItem);
            }
            //该商品在购物车中存在
            else {
                OrderItem orderItem = productMap.get(productId);
                //原数量+新增数量
                orderItem.setCount(orderItem.getCount() + productCount);
                //原总价+增加总价
                orderItem.setSubTotal(orderItem.getSubTotal() + product.getShopPrice() * productCount);
                productMap.put(productId, orderItem);
            }
        }
        List<OrderItem> orderItems = new ArrayList<>(productMap.values());
        return orderItems;
    }
}


