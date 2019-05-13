package com.oaec.hm.test;

import com.oaec.hm.entity.Passport;
import com.oaec.hm.entity.Person;
import com.oaec.hm.util.HibernateUtil;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class One2One {

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

    @Test
    public void add(){
        //创建人类对象
        Person person = new Person();
        person.setName("李四");
        person.setGender("女");
        person.setAge(17);

        //创建护照对象
        Passport passport = new Passport();
        passport.setBh("88888888888");

        //建立双向关联
        person.setPassport(passport);
        passport.setPerson(person);

        //只需要保存人类对象，会级联保存人类对象关联的护照对象
        long id = (long)session.save(person);
        System.out.println("自增id：" + id);
    }

}
