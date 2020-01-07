package com.wjx.springbootprac.repository;

import com.wjx.springbootprac.po.Apple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppleRepository extends JpaRepository<Apple,Integer> {

}
