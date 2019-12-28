package com.wjx.springbootprac.repository;

import com.wjx.springbootprac.po.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User getUserByUsername(String username);
}