package com.read.test.annotation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yfwangrui
 * Date: 14-7-26
 * Time: ����3:21
 * To change this template use File | Settings | File Templates.
 */
@Description("javaע�����")
public class AnnotationTest {

    @Override
    public boolean equals(Object obj){
        return false;
    }

    @Deprecated
    public void deprecated() {
        System.out.println("this is a deprecated method.");
    }

    @SuppressWarnings("unchecked")
    public void print(){
        List<String> list = new ArrayList();
    }

    @Name(originate = "���֣�����", community = "����")
    public String getName() {
        return "ha";
    }

    @Name(originate = "���֣�����", community = "����")
    public String getName2() {
        return "���ʱ����ˣ�";
    }

}
