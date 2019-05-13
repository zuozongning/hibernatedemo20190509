package com.oaec.hibernatedemo.test;

import com.oaec.hibernatedemo.entity.User;
import com.oaec.hibernatedemo.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class HelloHibernate {

    public static int add(String username, String password, String sex){
        //获取sessionFactory
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        //获取session对象
        Session session = sessionFactory.openSession();
        //开启事务
        session.beginTransaction();

        //创建一个User对象
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setSex(sex);

        //把user对象保存到数据库中
        int id = (int)session.save(user);

        //提交事务
        session.getTransaction().commit();

        System.out.println("自增的id：" + id);
        return id;
    }

    public static User get(int id){
        //获取sessionFactory
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        //获取session对象
        Session session = sessionFactory.openSession();
        //开启事务
        session.beginTransaction();

        User user = session.get(User.class, id);
        return user;
    }

    public static void update(int id, String username, String password, String sex){
        //获取sessionFactory
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        //获取session对象
        Session session = sessionFactory.openSession();
        //开启事务
        session.beginTransaction();

        //获取id对应的user对象
        User user = get(id);
        //更新user对象的属性
        user.setUsername(username);
        user.setPassword(password);
        user.setSex(sex);
        //更新到数据库
        session.update(user);

        session.getTransaction().commit();
        System.out.println("更新用户成功！");
    }

    public static void delete(int id){
        //获取sessionFactory
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        //获取session对象
        Session session = sessionFactory.openSession();
        //开启事务
        session.beginTransaction();

        User user = get(id);
        if(user!=null){
            session.delete(user);
            session.getTransaction().commit();
            System.out.println("删除用户成功！");
        }else{
            System.out.println("删除失败！id不存在！");
        }
    }

    public static void main(String[] args) {
        //add("tom","123456","男");
        get(1);
//        update(3, "jack", "666666","女");
        //delete(3);
    }
}
