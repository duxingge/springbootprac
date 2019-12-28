package com.wjx.springbootprac.service;

import com.wjx.springbootprac.po.Book;
import com.wjx.springbootprac.repository.JpaConnectTestRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
class JpaConnectTestServiceTest {

    @Autowired
    private JpaConnectTestRepository testRepository;

    @Test
    void findAll() {

    }

    @Test
    void save() {
        Book b = new Book();
        b.setName("红楼梦");
        b.setAuthor("曹雪芹");
        b.setAuthorAge(18);
        Book savebook = testRepository.save(b);
        Assert.assertEquals("曹雪芹",savebook.getAuthor());
    }
}