package com.oaec.hm.test;

import com.oaec.hm.entity.Course;
import com.oaec.hm.entity.Student;
import com.oaec.hm.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class Many2Many {

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
        //创建学生对象
        Student s1 = new Student();
        s1.setName("jack");
        s1.setSex("男");
        s1.setBirthday(new Date());

        Student s2 = new Student();
        s2.setName("mary");
        s2.setSex("女");
        s2.setBirthday(new Date());

        //创建课程对象
        Course c1 = new Course();
        c1.setName("Java");
        c1.setMajor("计算机");

        Course c2 = new Course();
        c2.setName("Oracle");
        c2.setMajor("计算机");

        Course c3 = new Course();
        c3.setName("Mysql");
        c3.setMajor("计算机");

        //建立关联关系
        s1.getCourses().add(c1);
        s1.getCourses().add(c3);

        s2.getCourses().add(c2);

        session.save(s1);
        session.save(s2);

        System.out.println("执行完成！");
    }

    @Test
    public void update(){

        Student student = session.get(Student.class, 1L);
        student.getCourses().clear();//清空原本选择的课程

        //获取id为3的课程
        Course course = session.get(Course.class, 3L);
        Course course2 = session.get(Course.class, 1L);

        student.getCourses().add(course);
        student.getCourses().add(course2);

        session.update(student);

        System.out.println("更新学生选课成功！");
    }
}
