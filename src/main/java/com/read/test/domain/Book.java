package com.read.test.domain;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yfwangrui
 * Date: 14-7-28
 * Time: 下午11:48
 * book POJO
 */
public class Book implements Serializable {

    private static final long serialVersionUID = -5091268679370931194L;
    public Integer id;

    private String name;

    private List<String> authors;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    private Object readResolve() throws ObjectStreamException {
        Book book = new Book();
        book.setName("readResolve");
        book.setId(2);
        return book;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
