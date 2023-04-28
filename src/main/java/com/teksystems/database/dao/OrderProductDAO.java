package com.teksystems.database.dao;

import com.teksystems.database.entity.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface OrderProductDAO extends JpaRepository<OrderProduct, Long> {

    //get product in order
    @Query(value="select op.* from\n" +
            "products p, orders o, order_products op\n" +
            "where \n" +
            "\top.order_id = :orderId\n" +
            "\tand op.product_id = :productId\n" +
            "group by op.id;", nativeQuery = true)
    OrderProduct findOrderProductById(Integer orderId, Integer productId);

    @Query(value="select p.id as \"product_id\", p.name, p.image_url, p.price, \n" +
            "op.id as \"order_products_id\", op.quantity,\n" +
            "o.order_status\n" +
            "from orders o, order_products op, products p\n" +
            "where\n" +
            "\to.id = :id and\n" +
            "\to.id = op.order_id and \n" +
            "    op.product_id = p.id;", nativeQuery = true)
    List<Map<String,Object>> findByCartByOrderId(Integer id);

    OrderProduct findById(Integer id);

}
