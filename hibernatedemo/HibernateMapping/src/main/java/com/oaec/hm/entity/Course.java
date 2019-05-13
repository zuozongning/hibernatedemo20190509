package com.oaec.hm.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="t_course")
public class Course {

    @Id //主键注解
    @GeneratedValue(strategy = GenerationType.IDENTITY)//主键生成策略
    private Long id;

    @Column(name="name",length = 100, unique = true, nullable = false)
    private String name;

    private String major;

    @ManyToMany(mappedBy = "courses")//配置多对多映射
    private List<Student> students = new ArrayList<Student>();

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", major='" + major + '\'' +
                '}';
    }
}
