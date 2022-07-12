package com.example.serviceclient.pojo;

import java.util.List;

/**
 * @Author wangjiaxing
 * @Date 2022/5/23
 */
public class People {
    private Book main;
    private List<Book> books;

    public Book getMain() {
        return main;
    }

    public void setMain(Book main) {
        this.main = main;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
