package com.wjx.springbootprac.service;

import com.wjx.springbootprac.po.Book;
import com.wjx.springbootprac.repository.JpaConnectTestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JpaConnectTestService {

    @Autowired
    private JpaConnectTestRepository testRepository;

    public List<Book> findAll() {
        return testRepository.findAll();
    }

    public Book save(Book b) {
        Book savebook = testRepository.save(b);
        return savebook;
    }

}
