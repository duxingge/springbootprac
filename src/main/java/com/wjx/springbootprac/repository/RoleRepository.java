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

    @Query("select r from t_role r,t_user_role ur where ur.uid=:uid and ur.rid = r.id")
    List<Role> getRolesByUid(@Param("uid") Integer uid);
}
