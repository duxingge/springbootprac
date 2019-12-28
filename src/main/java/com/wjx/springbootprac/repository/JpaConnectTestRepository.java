package com.wjx.springbootprac.repository;

import com.wjx.springbootprac.po.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaConnectTestRepository extends JpaRepository<Book,String> {

    @Query(value = "select d from t_book where age > :age and author not in (:authorName)")
    public List<Book> findAgeMoreAndNotAuthor(Integer age,String authorName);
}
