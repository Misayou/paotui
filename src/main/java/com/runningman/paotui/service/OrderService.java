package com.runningman.paotui.service;

import com.runningman.paotui.pojo.Order;
import com.runningman.paotui.pojo.OrderInfo;
import com.runningman.paotui.pojo.OrderTitle;
import com.runningman.paotui.pojo.OrdersUser;
import org.hibernate.validator.constraints.EAN;

import java.util.Date;
import java.util.List;


/**
 * @Auther: http://www.tanwei.com
 * @Date: 2019/10/23
 * @Description: com.runningman.paotui.service
 * @versio: 1.0
 */

public interface OrderService {
    /**
     * 根据用户id创建订单
     * @param user
     * @param order
     * @return
     */
    boolean makeOrder(String user,Order order);


    /**
     * 根据用户名查询order
     * @param user
     * @return
     */
    List<Order> selectOrderByUser(String user);

    /**
     * 显示所有的订单列表
     * @return
     */
    List<Order> getAllOrderList(int page,int limit);

    /**
     * 用户查看自己发布的订单列表
     * @param page
     * @param limit
     * @return
     */
    List<OrdersUser> getUserOrders(String user, int page, int limit);

    /**
     * 根据id在接单之前查看订单的详细内容
     * @param id
     * @return
     */
    OrderInfo getOrderInfo(int id);

    int getOrderCount();

    int getUserOrderCount(String user);

    /**
     * 设置订单的配送员
     * @param id
     * @param delivery
     * @return
     */
    void setDelivery(int id,String delivery);


    Date getOrderEndTime(int id);
}
