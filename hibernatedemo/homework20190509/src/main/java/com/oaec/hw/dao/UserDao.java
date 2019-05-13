package com.oaec.hw.dao;

import com.oaec.hw.entity.User;
import com.oaec.hw.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class UserDao {

    public int create(User user){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        int id = (int)session.save(user);
        session.getTransaction().commit();
        session.close();
        //sessionFactory.close();
        return id;
    }

    public User findByUsername(String username){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        String hql = "FROM User WHERE username=:username";
        Query<User> query = session.createQuery(hql, User.class);
        query.setParameter("username", username);
        User user = null;
        try {
            user = query.getSingleResult();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        session.getTransaction().commit();
        session.close();
        //sessionFactory.close();
        return user;
    }
}
