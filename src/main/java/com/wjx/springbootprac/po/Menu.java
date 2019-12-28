package com.wjx.springbootprac.po;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.List;

@Entity(name = "t_menu")
@Data
public class Menu {
    @Id
    private Integer id;

    /**
     * 访问url
     */
    private String pattern;

    @Transient
    private List<Role> roleList;
}
