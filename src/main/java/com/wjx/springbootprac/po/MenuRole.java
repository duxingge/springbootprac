package com.wjx.springbootprac.po;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "t_menu_role")
@Data
public class MenuRole {
    @Id
    private Integer id;

    /**
     * 菜单Id
     */
    private Integer mid;
    /**
     * 角色ID
     */
    private Integer rid;
}
