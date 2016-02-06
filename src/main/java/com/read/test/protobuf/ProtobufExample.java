//package com.read.test.protobuf;
//
//import com.google.protobuf.InvalidProtocolBufferException;
//import org.junit.Test;
//
///**
// * Created by yfwangrui on 2015/9/16.
// *
// * protobuf example
// */
//public class ProtobufExample {
//
//    @Test
//    public void test() {
//        PersonProtos.Person.Builder personBuilder = PersonProtos.Person.newBuilder();
//        personBuilder.setEmail("test@gmail.com");
//        personBuilder.setName("test");
//        personBuilder.setId(1);
//        PersonProtos.Person.PhoneNumber.Builder phoneBuilder = PersonProtos.Person.PhoneNumber.newBuilder();
//        phoneBuilder.setNumber("123456789");
//        phoneBuilder.setType(PersonProtos.Person.PhoneType.MOBILE);
//        personBuilder.addPhone(phoneBuilder);
//        PersonProtos.Person person = personBuilder.build();
//
//        byte[] data = person.toByteArray();
//        System.out.println(new String(data));
//        try {
//            System.out.println(PersonProtos.Person.parseFrom(data));
//        } catch (InvalidProtocolBufferException e) {
//            e.printStackTrace();
//        }
//    }
//}
