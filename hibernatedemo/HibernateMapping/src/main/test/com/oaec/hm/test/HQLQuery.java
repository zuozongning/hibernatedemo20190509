package com.oaec.hm.test;

import com.oaec.hm.entity.Course;
import com.oaec.hm.entity.OrderLine;
import com.oaec.hm.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class HQLQuery {

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

    //查询所有课程的信息
    @Test
    public void findAllCourse(){
        String hql = "from Course";//Course是类名，不是表名
        Query<Course> query = session.createQuery(hql, Course.class);
        List<Course> courseList = query.getResultList();
        for(Course course:courseList){
            System.out.println(course);
        }
    }

    //查询所有课程名称
    @Test
    public void findCourseName(){
        String hql = "select name from Course";
        Query query = session.createQuery(hql);
        List<String> nameList = query.getResultList();
        for(String name:nameList){
            System.out.println(name);
        }
    }

    //多条件查询课程(?号占位符)
    @Test
    public void findCourseByCondition(){
        String hql = "from Course where name like ?1 and major=?2";
        Query<Course> query = session.createQuery(hql, Course.class);

        query.setParameter(1, "%a%").setParameter(2, "计算机");
        List<Course> courseList = query.getResultList();

        for(Course course:courseList){
            System.out.println(course);
        }
    }

    //多条件查询课程(:字符串占位符)
    @Test
    public void findCourseByCondition2(){
        String hql = "from Course where name like :name and major=:major";
        Query<Course> query = session.createQuery(hql, Course.class);

        query.setParameter("name", "%a%").setParameter("major", "计算机");
        List<Course> courseList = query.getResultList();

        for(Course course:courseList){
            System.out.println(course);
        }
    }

    //分页查询订单明细
    @Test
    public void findByPage(){
        String hql = "from OrderLine";
        Query<OrderLine> query = session.createQuery(hql, OrderLine.class);
        query.setFirstResult(0);
        query.setMaxResults(2);
        List<OrderLine> orderLineList = query.getResultList();
        for(OrderLine ol:orderLineList){
            System.out.println(ol);
        }
    }

    //排序查询订单明细
    @Test
    public void findByOrderBy(){
        String hql = "from OrderLine order by price desc";
        Query<OrderLine> query = session.createQuery(hql, OrderLine.class);
        List<OrderLine> orderLineList = query.getResultList();
        for(OrderLine ol:orderLineList){
            System.out.println(ol);
        }
    }

    //查询函数
    @Test
    public void findByFunction(){
        String hql = "select count(*), max(price), sum(quantity) from OrderLine";
        Query<Object[]> query = session.createQuery(hql, Object[].class);
        Object[] objects = query.getSingleResult();

        System.out.println("订单明细总记录数：" + objects[0]);
        System.out.println("订单明细价格最高：" + objects[1]);
        System.out.println("订单明细总购买量：" + objects[2]);
    }

    //原生SQL查询
    @Test
    public void nativeQuery(){
        //定义原生的sql语句
        String sql = "select * from t_course where name=:name";
        NativeQuery sqlQuery = session.createSQLQuery(sql);
        Object[] objects = (Object[])sqlQuery.setParameter("name","Java").uniqueResult();

        Course course = new Course();
        course.setId(Long.parseLong(objects[0].toString()));
        course.setName(objects[1].toString());
        course.setMajor(objects[2].toString());
        System.out.println(course);
    }
}
