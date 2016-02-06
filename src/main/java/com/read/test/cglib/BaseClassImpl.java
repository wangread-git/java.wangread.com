package com.read.test.cglib;

/**
 * Created by wang.read on 2016/1/28.
 *
 *
 */
public class BaseClassImpl implements BaseClass {

    public String words;

    public void foo() {
        System.out.println(words);
    }

    public void setWords(String words) {
        this.words = words;
    }

    public String getWords() {
        return words;
    }
}
