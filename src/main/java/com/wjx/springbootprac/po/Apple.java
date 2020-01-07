package com.wjx.springbootprac.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "t_apple")
@NoArgsConstructor
@Entity
public class Apple {

    @Id
    @GeneratedValue
    private Integer id;

    /**
     *  重量
     */
    private Integer weight;

    /**
     *  颜色
     */
    private String color;

    public Apple(Integer weight, String color) {
        this.weight = weight;
        this.color = color;
    }
}
