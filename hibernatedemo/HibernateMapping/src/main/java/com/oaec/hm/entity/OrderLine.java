package com.oaec.hm.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="t_order_line")
public class OrderLine {
    @Id //主键注解
    @GeneratedValue(strategy = GenerationType.IDENTITY)//主键生成策略
    private Long id;

    @Column(name = "price",columnDefinition = "double(10,2) default '0.00'")
    private Double price;

    @Column(name="quantity", length = 10)
    private Long quantity;

    @Column(name="product", length = 100, nullable = false)
    private String product;

    @ManyToOne
    @JoinColumn(name="order_id")//配置外键
    private Order order;

    @Override
    public String toString() {
        return "OrderLine{" +
                "id=" + id +
                ", price=" + price +
                ", quantity=" + quantity +
                ", product='" + product + '\'' +
                '}';
    }
}
