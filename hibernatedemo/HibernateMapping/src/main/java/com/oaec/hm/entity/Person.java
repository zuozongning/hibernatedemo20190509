package com.oaec.hm.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * 人类
 */
@Data
@Entity
@Table(name="t_person")
public class Person {
    @Id //主键注解
    @GeneratedValue(strategy = GenerationType.IDENTITY)//主键生成策略
    private Long id;

    private String name;
    private String gender;
    private Integer age;

    //一对一映射，mappedBy代表passport属性里和本实体类关系
    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
    private Passport passport;
}
