package com.read.test.kryo;

import com.caucho.hessian.io.HessianOutput;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.read.test.domain.Programmer;
import com.read.test.hessian.HessianTest;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by wang.read on 2016/3/10.
 * <p>
 * kryo example
 */
public class KryoExample {

    @Test
    public void test() {
        Programmer programmer = new Programmer();
        programmer.setId(1);
        programmer.setName("wangread");
        programmer.setAge(20);
        programmer.setMale(true);
        programmer.setGitHubSite("https://github.com/wangread-git");

        int count = 1000000;

        long start = System.nanoTime();
        System.out.println("hessian length:" + HessianTest.serialize(programmer).length);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        HessianOutput ho = new HessianOutput(os);
        for (int i = 0; i < count; i++) {
            try {
                ho.writeObject(programmer);
            } catch (IOException e) {
                e.printStackTrace();
            }
//            os.toByteArray();
        }
        long end = System.nanoTime();
        System.out.println("hessian time:" + (end - start));
        System.out.println("hessian avg time:" + ((end - start) / count));

        Kryo kryo = new Kryo();
        kryo.register(Programmer.class);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        Output output = new Output(bos);
        start = System.nanoTime();
        kryo.writeObject(output, programmer);
        byte[] bytes = output.toBytes();
        System.out.println("kryo length:" + bytes.length);
        for (int i = 0; i < count; i++) {
            kryo.writeObject(output, programmer);
//            output.toBytes();
        }
        end = System.nanoTime();
        System.out.println("kryo time:" + (end - start));
        System.out.println("kryo avg time:" + ((end - start) / count));
        //如果要使用传给Output的OutputStream的话，记得在output写完后调用flush方法
//        output.flush();
//        System.out.println("bos length:" + bos.toByteArray().length);
        //会将传给output的OutputStream一并关闭
        output.close();

        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        Input input = new Input(bis);
        Programmer newProgrammer = kryo.readObject(input, Programmer.class);
        System.out.println(newProgrammer);
        input.close();
    }
}
