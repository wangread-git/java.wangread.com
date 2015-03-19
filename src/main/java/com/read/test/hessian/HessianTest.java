package com.read.test.hessian;

import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: yfwangrui
 * Date: 14-8-26
 * Time: ÏÂÎç2:26
 * To change this template use File | Settings | File Templates.
 */
public class HessianTest {

    public static byte[] serialize(Object obj) {
        if(obj==null) throw new NullPointerException();

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        HessianOutput ho = new HessianOutput(os);
        try {
            ho.writeObject(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return os.toByteArray();
    }

    public static Object deserialize(byte[] bytes) {
        if(bytes==null) throw new NullPointerException();

        ByteArrayInputStream is = new ByteArrayInputStream(bytes);
        HessianInput hi = new HessianInput(is);
        try {
            return hi.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
