package com.oaec.ha.entity;

import javax.persistence.*;

@Entity //持久化类注解
@Table(name="t_teacher") //表名映射
public class Teacher {

    @Id //主键注解
    @GeneratedValue(strategy = GenerationType.IDENTITY)//主键生成策略
    private Long id;//主键

    @Column(name="tname",length = 32, unique = true, nullable = false)
    private String name;//姓名

    private String sex;//性别

    @Column(name="age", length = 3)
    private Integer age;//年龄

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                '}';
    }
}
