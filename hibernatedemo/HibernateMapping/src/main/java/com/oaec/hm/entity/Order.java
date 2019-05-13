package com.oaec.hm.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name="t_order")
public class Order {
    @Id //主键注解
    @GeneratedValue(strategy = GenerationType.IDENTITY)//主键生成策略
    private Long id;

    @Column(name="ordered_date")
    private Date orderedDate;
    @Column(name="shipped_date")
    private Date shippedDate;
    @Column(name = "total",columnDefinition = "double(10,2) default '0.00'")
    private Double total;

    //一对多的映射配置
    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private List<OrderLine> orderlines = new ArrayList<OrderLine>();
}
