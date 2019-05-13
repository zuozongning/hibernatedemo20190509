package com.oaec.hm.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name="t_stu")
public class Student {

    @Id //主键注解
    @GeneratedValue(strategy = GenerationType.IDENTITY)//主键生成策略
    private Long id;

    private String name;
    private String sex;
    private Date birthday;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="t_stu_course",
            joinColumns = {@JoinColumn(name="student_id")},
            inverseJoinColumns = {@JoinColumn(name="course_id")})//配置桥表t_student_course
    private List<Course> courses = new ArrayList<Course>();//课程集合
}
