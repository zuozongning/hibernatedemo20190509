package com.oaec.ha.test;

import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;

public class HibernateTest {

    private Session session;

    @Before
    public void openSession(){
        session = HibernateUtil.getSession();
    }

    @After
    public void closeSession(Session session){
        HibernateUtil.closeSession(session);
    }

}
