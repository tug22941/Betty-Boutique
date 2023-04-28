package com.teksystems.database.dao;

import com.teksystems.database.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface OrderDAO extends JpaRepository <Order, Long> {

    @Query(value="select * from orders where id = :id ;", nativeQuery = true)
    Order findById(Integer id);

    @Query(value="select * from orders where order_status = 'Open' and user_id = :id ;", nativeQuery = true)
    Order findOpenOrder(Integer id);

    @Query(value="select * from orders o where o.user_id = :id and o.order_status <> \"Open\" ;", nativeQuery = true)
    List<Order> findPastOrders(Integer id);

    @Query(value="select o.shipping_date, o.order_status, o.total, o.receiver,\n" +
            "op.quantity,\n" +
            "p.name, p.image_url\n" +
            "from orders o, order_products op, products p\n" +
            "where \n" +
            "\to.id = :id and\n" +
            "    op.product_id = p.id and\n" +
            "    o.id = op.order_id;", nativeQuery = true)
    List<Map<String,Object>> findOrderInformation(Integer id);

}
