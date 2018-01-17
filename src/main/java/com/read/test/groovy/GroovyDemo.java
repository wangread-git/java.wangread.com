package com.read.test.groovy;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import groovy.lang.Script;
import org.junit.Test;

/**
 * Created by bjyfwangrui on 2018/1/17.
 */
public class GroovyDemo {

    @Test
    public void testGroovyShell() {
        Binding binding = new Binding();
        binding.setVariable("name", "zhangsan");

        GroovyShell shell = new GroovyShell(binding);
        Object obj1 = shell.evaluate("'Hello World! I am ' + name");
        System.out.println(obj1.getClass());
        System.out.println(obj1);

        String scriptText = "import org.apache.commons.lang.StringUtils; return StringUtils.isEmpty(name)";
        Object obj2 = shell.evaluate(scriptText);
        System.out.println(obj2.getClass());
        System.out.println(obj2);

        Object obj3 = shell.evaluate("name == 'zhangsan'");
        System.out.println(obj3.getClass());
        System.out.println(obj3);

        Script script = GroovyScriptHelper.getInstance(scriptText, binding, true);
        Object obj4 = script.run();
        System.out.println(obj4.getClass());
        System.out.println(obj4);
    }
}
