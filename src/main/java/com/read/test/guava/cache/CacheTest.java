package com.read.test.guava.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.read.test.domain.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

/**
 * Created with IntelliJ IDEA.
 * User: yfwangrui
 * Date: 14-7-28
 * Time: …œŒÁ11:39
 * guava cache≤‚ ‘
 */
public class CacheTest {
    private static Cache<String, Object> cache = CacheBuilder.newBuilder().maximumSize(1000).build();

    public static void main(String[] args) {

        String key = "abcd";
        try {
            Object bookList = cache.get(key, new Callable<Object>() {
                public Object call(){
                    return NullObject.NULL_OBJECT;
                }
            });
            if (bookList == null || bookList == NullObject.NULL_OBJECT) {
                List<Book> books = new ArrayList<Book>();
                for (int i = 0; i < 4; i++) {
                    Book book = new Book();
                    book.setId(i);
                    book.setName("book" + i);
                    books.add(book);
                }
                cache.put(key, books);
            }
            bookList = cache.get(key, new Callable<Object>() {
                public Object call(){
                    return NullObject.NULL_OBJECT;
                }
            });
            for (Book book : ((List<Book>)bookList)) {
                System.out.println(book.getId() + ":" + book.getName());
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
