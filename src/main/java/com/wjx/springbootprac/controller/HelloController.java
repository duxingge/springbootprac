package com.wjx.springbootprac.controller;

import com.wjx.springbootprac.po.Book;
import com.wjx.springbootprac.repository.JpaConnectTestRepository;
import com.wjx.springbootprac.common.util.ResultUtil;
import com.wjx.springbootprac.vo.BookVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
public class HelloController {

    @Autowired
    private JpaConnectTestRepository repository;


    @GetMapping("/books")
    public ModelAndView getBooks() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("books");
        modelAndView.addObject("books",genData());
        return modelAndView;
    }

    @ResponseBody
    @GetMapping("/booksJson")
    public Object getBookJson() {
        return  genData();
    }


    @ResponseBody
    @GetMapping("/exception")
    public Object exceptiionTest() {
        int i = 1/0;
        return "s";
    }



    private List<BookVO> genData() {
        BookVO b1 = new BookVO("1","红楼梦","曹雪芹");
        BookVO b2 = new BookVO("2","三国演义","罗贯中");
        List<BookVO> bookVOList = new ArrayList<BookVO>();
        bookVOList.add(b1);
        bookVOList.add(b2);
        return bookVOList;
    }

    @ResponseBody
    @GetMapping("/global")
    private Object globalTest(Model model,@ModelAttribute("b.p1") String p1) {
        Map m = model.asMap();
        Map ownerMap = (Map) m.get("owner");
        Set set = ownerMap.keySet();
        Iterator iterator = set.iterator();
        if (iterator.hasNext()) {
            Object next = iterator.next();
            System.out.println(ownerMap.get(next));
        }
        return p1;
    }


    @ResponseBody
    @GetMapping("/book")
    private Object addBook(Book b) {
        Book savebook = repository.save(b);
        return ResultUtil.success(savebook);
    }

}
