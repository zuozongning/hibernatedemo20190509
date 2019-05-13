package com.oaec.ha.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * 护照类
 */
@Data
@Entity
@Table(name="t_passport")
public class Passport {
    @Id //主键注解
    @GeneratedValue(strategy = GenerationType.IDENTITY)//主键生成策略
    private Long id;

    private String bh;

    @OneToOne
    @JoinColumn(name="person_id")//配置外键列
    private Person person;
}
