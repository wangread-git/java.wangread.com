package com.read.test.groovy;

import com.read.test.velocity.VelocityUtils;
import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import groovy.lang.Script;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.runtime.parser.ParseException;
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

        Script script = GroovyScriptHelper.getInstance(scriptText, binding);
        Object obj4 = script.run();
        System.out.println(obj4.getClass());
        System.out.println(obj4);
    }

    @Test
    public void testCompare() {
        Binding binding = new Binding();
        binding.setVariable("name", "zhangsan");
        String scriptText = "import org.apache.commons.lang.StringUtils; return StringUtils.isEmpty(name)";
        Script script = GroovyScriptHelper.getInstance(scriptText, binding);
//        script.run();
        long scriptStart = System.currentTimeMillis();
        for (int i = 0; i < 50000; i++) {
            script.run();
        }
        long scriptEnd = System.currentTimeMillis();
        System.out.println("Groovy Script : " + (scriptEnd - scriptStart));

        VelocityContext context = new VelocityContext();
        context.put("StringUtils", StringUtils.class);
        context.put("name", "zhangsan");
        String str = "$StringUtils.isEmpty($name)";
        try {
            VelocityUtils.merge(str, context);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long velocityStart = System.currentTimeMillis();
        for (int i = 0; i < 50000; i++) {
            try {
                VelocityUtils.merge(str, context);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        long velocityEnd = System.currentTimeMillis();
        System.out.println("Velocity : " + (velocityEnd - velocityStart));
    }
}
