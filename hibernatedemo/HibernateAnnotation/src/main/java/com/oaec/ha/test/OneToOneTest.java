package com.oaec.ha.test;

import com.oaec.ha.entity.Passport;
import com.oaec.ha.entity.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;

import java.io.Serializable;

public class OneToOneTest {

    public static void add(){
        //创建人类对象
        Person person = new Person();
        person.setName("张三");
        person.setGender("男");
        person.setAge(19);

        //创建护照对象
        Passport passport = new Passport();
        passport.setBh("123456789");

        //建立双向关联
        person.setPassport(passport);
        passport.setPerson(person);

        //只需要保存人类对象，会级联保存人类对象关联的护照对象
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        long id = (long)session.save(person);
        System.out.println("自增id：" + id);

        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
    }

    public static Person getById(long id){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Person person = session.get(Person.class, id);

        session.getTransaction().commit();
        session.close();
        return person;
    }

    @Test
    public static void delete(long id){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Person person = getById(id);

        //级联删除人类以及关联的护照记录
        session.delete(person);

        session.getTransaction().commit();
    }
    public static void main(String[] args) {
//        Person person = getById(2L);
//        System.out.println("姓名："+person.getName());
//        System.out.println("护照号："+person.getPassport().getBh());

        delete(2L);

//        add();
    }
}
