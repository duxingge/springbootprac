package com.wjx.springbootprac.po;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "t_role")
@Data
public class Role {

    @Id
    private Integer id;

    /**
      *角色名英文
      */
    private String name;
    /**
     * 角色名中文
     */
    private String namezh;
}
