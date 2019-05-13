package com.oaec.hm.test;

import com.oaec.hm.entity.Order;
import com.oaec.hm.entity.OrderLine;
import com.oaec.hm.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;

public class One2Many {

    private Session session;

    //在执行Test方法前，调用此方法
    @Before
    public void getSession(){
        session = HibernateUtil.getSession();
        session.beginTransaction();//开启事务
    }

    //在执行Test方法后，调用此方法
    @After
    public void closeSession(){
        session.getTransaction().commit();
        HibernateUtil.closeSession(session);
    }

    //创建订单
    @Test
    public void add(){
        //创建订单对象
        Order order = new Order();
        order.setOrderedDate(new Date());

        //创建订单明细
        OrderLine ol1 = new OrderLine();
        ol1.setProduct("花生");
        ol1.setPrice(4.0);
        ol1.setQuantity(200L);

        OrderLine ol2 = new OrderLine();
        ol2.setProduct("芝麻");
        ol2.setPrice(8.0);
        ol2.setQuantity(100L);

        //计算订单总价
        Double total = ol1.getPrice()*ol1.getQuantity()
                + ol2.getPrice()*ol2.getQuantity();

        order.setTotal(total);

        //订单与订单明细关联
        order.getOrderlines().add(ol1);
        order.getOrderlines().add(ol2);
        ol1.setOrder(order);
        ol2.setOrder(order);

        //保存订单对象，会级联保存订单明细
        long id = (long)session.save(order);
        System.out.println("创建订单成功！订单id为：" + id);
    }

    //按订单id获取订单
    @Test
    public void getOrderById(){
        Order order = session.get(Order.class, 1L);
        System.out.println(order);
    }

    //查询所有订单
    @Test
    public void getAllOrder(){

        String hql = "from Order";

        Query<Order> query = session.createQuery(hql, Order.class);
        List<Order> orderList = query.getResultList();

        for(Order order:orderList){
            System.out.println(order);
        }
    }
}
