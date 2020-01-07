package com.wjx.springbootprac.service;

import com.wjx.springbootprac.po.Apple;
import com.wjx.springbootprac.repository.AppleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class AppleService {

    @Autowired
    private AppleRepository appleRepository;

    public List<Apple> getApple(Predicate<Apple> t){
        List<Apple> all = appleRepository.findAll();
        return all.parallelStream().filter(t).collect(Collectors.toList());
    }

    public Apple saveApple(Apple a) {
        return appleRepository.save(a);
    }

}
