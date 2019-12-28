package com.wjx.springbootprac.repository;

import com.wjx.springbootprac.po.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
    List<Role> getRolesByIdIn(List<Integer> roleIdList);

    //通过用户ID得到用户的所有角色
    @Query("select r from t_role r,t_user_role ur where ur.uid=:uid and ur.rid = r.id")
    List<Role> getRolesByUid(@Param("uid") Integer uid);
    //通过菜单ID获得所有菜单的拥有角色
    @Query("select r from t_role r,t_menu_role mr where mr.mid=:mid and mr.rid = r.id")
    List<Role> getRolesByMid(@Param("mid") Integer mid);
}
