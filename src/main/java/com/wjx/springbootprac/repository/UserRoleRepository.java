package com.wjx.springbootprac.repository;

import com.wjx.springbootprac.po.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole,Integer> {
    List<UserRole> getUserRolesByUid(Integer uid);
}
