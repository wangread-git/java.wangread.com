package com.read.test.hessian;

import com.read.test.domain.Book;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * Created with IntelliJ IDEA.
 * User: yfwangrui
 * Date: 14-8-26
 * Time: ÏÂÎç2:30
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main(String[] args) {
//        List<Book> bookList = new ArrayList<Book>();
//        for (int i = 0; i < 10; i++) {
//            Book book = new Book();
//            book.setId(1);
//            book.setName("Ãû³Æ" + i);
//            bookList.add(book);
//        }
//        byte[] bytes = HessianTest.serialize(bookList);
//        bookList.get(0).setName("¹þ¹þ");
//        List<Book> books = (List<Book>) HessianTest.deserialize(bytes);
//        for (Book book : books) {
//            System.out.println(book.toString());
//        }
//        Map<String, String> map = new HashMap<String, String>();
//        String pin = null;
//        map.put("nm", "nm");
//        System.out.println("nm".equals(map.get(pin)));

        Class bookCl = Book.class;
        try {
            Constructor<Book> bookCs = bookCl.getDeclaredConstructor(null);
            bookCs.setAccessible(true);
            Book book = bookCs.newInstance();
            Field[] fields = bookCl.getDeclaredFields();
            for (Field field : fields) {
                if (field.getName().equals("name")) {
                    field.setAccessible(true);
                    field.set(book, "balabala");
                }
                if (field.getName().equals("id")) {
                    field.setAccessible(true);
                    field.set(book, 1);
                }
            }
            System.out.println(book);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
