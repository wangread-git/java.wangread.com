package com.read.test.annotation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yfwangrui
 * Date: 14-7-26
 * Time: 下午3:21
 * To change this template use File | Settings | File Templates.
 */
@Description("java注解测试")
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

    @Name(originate = "名字：王锐", community = "京东")
    public String getName() {
        return "ha";
    }

    @Name(originate = "名字：老邢", community = "乐视")
    public String getName2() {
        return "工资暴击了！";
    }

}
