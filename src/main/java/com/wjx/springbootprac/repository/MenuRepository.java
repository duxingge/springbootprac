package com.wjx.springbootprac.repository;

import com.wjx.springbootprac.po.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu,Integer> {
}
