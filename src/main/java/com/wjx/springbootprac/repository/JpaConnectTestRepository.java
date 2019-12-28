package com.wjx.springbootprac.repository;

import com.wjx.springbootprac.po.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaConnectTestRepository extends JpaRepository<Book,String> {

}
