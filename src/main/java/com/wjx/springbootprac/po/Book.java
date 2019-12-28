package com.wjx.springbootprac.po;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "t_book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "book_name")
    private String name;

    private String author;

    private Integer authorAge;

}
